import request from '@/utils/request'

export default{
  // 查询所有菜单数据
  getAllMenu(){
    return request({
      url: '/sys/menu',
      method: 'get',
    });
  },
}
