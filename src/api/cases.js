
// 腾讯疫情数据的接口
/**
 * https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=statisGradeCityDetail,diseaseh5Shelf
 */
/**
 * @param(modules) {value:diseaseh5Shelf}
 *
 */
// 获取疫情数据
import request from '@/utils/request'
const tencent = '/tencent'
export function getdata() {
  return request({
    url: tencent + '?modules=diseaseh5Shelf',
    method: 'post'
  })
}

// 每日新增数据

export function chinaDayAddList() {
  return request({
    url: tencent + '?modules=chinaDayAddList',
    method: 'post'
  })
}

// 每日累积新增
export function chinaDayList() {
  return request({
    url: tencent + '?modules=chinaDayList',
    method: 'post'
  })
}

