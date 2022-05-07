import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { Message } from 'element-ui'
// import user from 'mock/user'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // 用户登录
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      console.log(userInfo) // for debug
      // 服务器需要的登录参数
      const payload = { 'username': username, 'password': password }
      // 请求服务器
      console.log(JSON.stringify(payload))
      // 前端传送的为JSON对象，这里需要转换为字符串
      login(JSON.stringify(payload)).then(response => {
        const { data } = response
        console.log('response=')
        console.log(response) // for debug
        // 登录成功-消息提示
        Message({
          message: response.message || 'Success',
          type: 'success',
          duration: 5 * 1000
        })
        // 设置登录后的token
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      // getInfo(state.token).then(response => {
      //   const { data } = response
      // 此处没有写接口，先固定
      const { data } = {
        data: {
          name: 'admin',
          avatar: 'https://cdn.jsdelivr.net/gh/code-jie123/code-jie123cdn/user.jpg'
        }
      }
      if (!data) {
        return reject('登录已经过期，请重新登录')
      }

      const { name, avatar } = data

      commit('SET_NAME', name)
      commit('SET_AVATAR', avatar)
      resolve(data)
      // }).catch(error => {
      // reject(error)
      // })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

