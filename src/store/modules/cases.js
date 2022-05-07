import { getList } from '@/api/table'
import { chinaDayAddList } from '@/api/cases'
const state = {
  Caselist: [], // 从后端获取的病例数据
  chinaDayAddList: [], // 中国新增数据
  chinaTotal: [] // 中国累积数据
}

const mutations = {
  GET_CASES: (state, list) => {
    state.Caselist = list
    //console.log('caselist已经提交')
    //console.log(list)
  },
  REMOVE_CASE: (state, index) => {
    state.Caselist.splice(index, 1)
    //console.log('已经删除' + index)
  },
  GET_DAILY: (state, daily) => {
    state.chinaDayAddList = daily
    console.log('已经获取到每日新增数据')
    console.log(daily)
  }
}

const actions = {
  // 获取后台数据给CaseList
  fetchcases({ commit }) {
    return new Promise(
      (reject) => {
        getList().then(response => {
          console.log('已经提交给get_case')
          commit('GET_CASES', response.data)
        }).catch(error => {
          reject(error)
        })
      }
    )
  },
  // 去除caselist的一个案例
  removecase({ commit }, index) {
    commit('REMOVE_CASE', index)
    console.log('remove已经提交')
  },
  // 获取每日新增数据
  getDaily({ commit }) {
    return new Promise((reject) => {
      chinaDayAddList().then(response => {
        console.log('每日新增数据已获取提交')
        //console.log('data=')
       // console.log(response.data.chinaDayAddList)
        commit('GET_DAILY', response.data.chinaDayAddList)
      }).catch(error => {
        reject(error)
      })
    })
  }

}

export default {
  namespaced: true,
  mutations,
  actions,
  state
}
