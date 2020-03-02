package com.nowcoder.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    // 定义一个常量，当检测到敏感词时使用该常量来替换
    private static final String REPLACEMENT = "***";

    // 初始化空的根节点
    private TrieNode rootNode = new TrieNode();

    // 当容器实例化bean并调用构造器、处理注入之后，自动调用该方法
    // 该方法不能有任何入参，必须为void
    // 在服务启动时对敏感词树进行初始化
    @PostConstruct
    public void init() {


        try (
                // 项目编译之后结果会输出到项目的target目录下，getClassLoader()获取class目录下的文件
                InputStream is = this.getClass().getClassLoader().getResourceAsStream(Constants.SENSITIVE_FILE);
                // 使用InputStreamReader读取is的内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String keyWord;
            while ((keyWord = reader.readLine()) != null) {
                //当读取到本行敏感词之后，将敏感词添加到前缀树
                this.addKeyWord(keyWord);
            }
        } catch (IOException e) {
            logger.error("加载敏感词文件失败" + e.getMessage());
        }
    }

    // 将敏感词添加到前缀树
    private void addKeyWord(String keyWord) {
        // 构建一个临时节点，默认指向根节点
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyWord.length(); i++) {
            char c = keyWord.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);
            if (subNode == null) {
                // 初始化子节点
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }
            // 指针指向子节点，进入下一轮循环
            tempNode = subNode;

            //最后一个字符标记结束
            if (i == keyWord.length() - 1) {
                tempNode.setKeyWordEnd(true);
            }
        }
    }

    // 返回不含敏感词的字符串

    /**
     * 过滤敏感词
     *
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        // 过滤时依赖三个指针：1-指向根节点 2-指向当前词 3-跑词指针
        TrieNode tempNode = rootNode;
        int begin = 0;
        int position = 0;
        //结果
        StringBuilder sb = new StringBuilder();
        while (position < text.length()) {
            char c = text.charAt(position);

            //跳过标点符号，比如＆开＆票*这样的
            if (isSymbol(c)) {
                // 若指针1处于根节点，将此符号记入结果，让指针2向下走一步
                if (tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
                // 无论符号在开头或者中间，指针3都向下走一步
                position++;
                continue;
            }

            // 检测下级节点
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                // 以begin开头的字符串不是敏感词
                sb.append(text.charAt(begin));
                // 进入下一个位置
                position = ++begin;
                tempNode = rootNode;
            } else if (tempNode.isKeyWordEnd()) {
                //发现了敏感词，begin开头，position结尾，需要替换掉
                sb.append(REPLACEMENT);
                // 进入下一个位置
                begin = ++position;
                tempNode = rootNode;
            } else {
                // 当前未至叶子节点，继续向下
                position++;
            }
        }
        // 将最后一批字符记入结果
        sb.append(text, begin, position);
        return sb.toString();
    }

    //判断是否为符号
    private boolean isSymbol(Character c) {
        // 0x2E80-0x9FFF是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    // 前缀树的基本结构定义
    private class TrieNode {
        // 关键词结束的标识
        private boolean isKeyWordEnd = false;

        public boolean isKeyWordEnd() {
            return isKeyWordEnd;
        }

        public void setKeyWordEnd(boolean keyWordEnd) {
            isKeyWordEnd = keyWordEnd;
        }

        //当前节点的子节点(key是下级字符，value是下级节点)
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        // 添加子节点的方法
        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        // 获取子节点
        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }
    }
}
