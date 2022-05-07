const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  caselist: state => state.cases.Caselist,
  chinaAdd: state => state.cases.chinaDayAddList,
  chinaTotal: state => state.cases.chinaTotal
}
export default getters
