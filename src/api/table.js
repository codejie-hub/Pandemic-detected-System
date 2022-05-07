import request from '@/utils/request'
const api = '/api'
export function getList() {
  return request({
    url: api + '/getallcase',
    method: 'post'
  })
}

export function deleCase(cid) {
  return request({
    url: api + '/deleCase',
    method: 'post',
    params: { cid }
  })
}
// 添加病例
export function addCase(cases) {
  return request({
    url: api + '/addCase',
    method: 'post',
    params: cases
  })
}

// 更新病例
export function updateCase(cases) {
  return request({
    url: api + '/updateCase',
    method: 'post',
    params: cases
  })
}
