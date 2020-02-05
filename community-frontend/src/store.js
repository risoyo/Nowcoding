import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

const state = {
  count: 0,
  userInfo: {
    userID: "3875018461",
    user: "学员2",
    learnTime: "120h",
    lastLogin: "2019-09-24"
  },
  unComTrain: {
    planID1: "1360355848",
    planName1: "新中平架构与开发",
    planID2: "1360355849",
    planName2: "代理地方财政培训"
  },
  unComCourse: {
    courseID1: "42057497",
    courseName1: "新中平技术架构介绍",
    courseID2: "3",
    courseName2: "代理财政内外联介绍"
  },
  //todo 使用module重构
  //train plan
  allCourses: [
    { name: "java基础", id: 1 },
    { name: "c程序设计", id: 2 },
    { name: "spring框架", id: 3 },
    { name: "中间业务平台", id: 4 },
    { name: "postgresql入门", id: 5 },
    { name: "代理财政", id: 6 },
    { name: "云平台", id: 7 }
  ],
  allStudents: [
    { name: "学生一", id: 1 },
    { name: "学生二", id: 2 },
    { name: "学生三", id: 3 },
    { name: "学生四", id: 4 },
    { name: "学生五", id: 5 },
    { name: "学生六", id: 6 },
    { name: "学生七", id: 7 }
  ],
  page: {
    read: 0,
    sum: 0
  },
  newInterBp: {
    planId: "1360355848",
    planName: "新中平架构与开发",
    planDesc: "201909班",
    courseCount: 7,
    startTime: "2019年9月30日",
    endTime: "2019年10月30日",
    examTime: "2019年11月5日",
    introduction:
      "系统的介绍了新中平的相关知识，帮助理解和掌握新中平的开发方法",
    desc: "针对新中平的培训"
  },
  // newInterBpResource: {
  //   S42057497: "course/新中平/01 新中平技术架构介绍.pdf"
  // }
  newInterBpResource: [
    { id: 42057497, url: "course/新中平/01 新中平技术架构介绍.pdf" },
    { id: 42057498, url: "course/新中平/02 新中平业务架构介绍.pdf" },
    { id: 42057499, url: "course/新中平/03 新中平联机业务介绍.pdf" },
    { id: 42057500, url: "course/新中平/04 新中平批量业务介绍.pdf" },
    { id: 42057501, url: "course/新中平/05 新中平触发架构介绍.pdf" },
    { id: 42057502, url: "course/新中平/06 新中平日终架构介绍.pdf" },
    { id: 42057503, url: "course/新中平/07 新中平接口开发介绍.pdf" }
  ]
};

const mutations = {
  mutationsAddCount(state, n = 0) {
    return (state.count += n);
  },
  mutationsReduceCount(state, n = 0) {
    return (state.count -= n);
  },
  mutationsPageValue(state, n) {
    console.log("testM: " + n.read + ":" + n.sum);
    state.page.read += 1;
    state.page.sum += 1;
    console.log("state.page: " + state.page.read + ":" + state.page.sum);
    return state.page;
  }
};

const actions = {
  actionsAddCount(context, n = 0) {
    console.log(context);
    return context.commit("mutationsAddCount", n);
  },
  actionsReduceCount({ commit }, n = 0) {
    return commit("mutationsReduceCount", n);
  },
  actionSetPage({ commit }, n) {
    console.log("test:" + n);
    return commit("mutationsPageValue", n);
  }
};

const getters = {
  getterUserInfo(state) {
    return state.userInfo;
  },
  getterunComTrain(state) {
    return state.unComTrain;
  },
  getterunComCourse(state) {
    return state.unComCourse;
  },
  //train plan
  getAllCourses(state) {
    return state.allCourses;
  },
  getAllStudents(state) {
    return state.allStudents;
  },
  getPage(state) {
    return state.page;
  },
  //plandetail
  getternewInterBp(state) {
    return state.newInterBp;
  },
  newInterBpResource(state) {
    return state.newInterBpResource;
  }
};

export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
      reducer(val) {
        return {
          // 只储存state中的count
          count: val.count
        };
      }
    })
  ]
});
