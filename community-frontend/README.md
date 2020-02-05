# TrainSystem

#### 一、安装node
https://nodejs.org/en/


版本：10.16.3


安装git：

https://git-scm.com/




#### 二、clone项目
`git clone https://gitee.com/yinglongnvba/TrainSystem.git`


进入文件夹


`cd TrainSystem`



#### 三、运行


`npm install`




`npm run serve`



#### 四、开发参考


Vue全家桶+SSR+Koa2全栈开发美团网


`课程列表页`--`第11章 美团网产品列表页`


`课程详情页`--`第12章 美团网产品详情页开发`


`我的课程`--`第14章 订单页开发`



在新建了页面或者vue文件之后项目内添加注释，也记得更新这个文档


#### 六、开发工具


推荐Vscode，可以用idea

https://code.visualstudio.com/

使用vscode的话要装几个插件：


`Vetur`


`open in browser`


`ESLint`


安装之后进入vscode，点击左上角`file`--`preferences`--`settings`，输入`git.path`，然后点击`edit in settings.json`

将内容填充为：


```js
{
    "editor.formatOnType": true,
    "editor.formatOnSave": true,
    "editor.wordWrap": "on",
    "editor.minimap.enabled": false,
    "files.autoSave": "afterDelay",
    "eslint.validate": [
        "javascript",
        "javascriptreact",
        {
            "language": "vue",
            "autoFix": true
        }
    ],
    "eslint.autoFixOnSave": true,
    "vetur.format.defaultFormatter.js": "vscode-typescript",
    "git.path": "C:/Program Files/Git/bin/git.exe",
    "git.autofetch": true,
}
```

注意：若第一步中git非默认路径安装，这里需要修改`git.path`为安装路径


#### 七、开发注意

默认在Develop分支下开发，注意使用vscode左下角当前分支


![输入图片说明](https://images.gitee.com/uploads/images/2019/0929/031635_220c0c9c_762520.png "屏幕截图.png")



在完成完整单页面之后定期merge到master分支

#### 八、部署排坑

部署命令：
```js
npm run build
```

但是有两点要注意的

1. 在`vue.config.js`中添加`publicPath: "./"`

2. 在`router.js`中关闭`history`模式