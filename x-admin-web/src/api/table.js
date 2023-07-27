import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/sys/user/all',
    method: 'get',
    params
  })
}
