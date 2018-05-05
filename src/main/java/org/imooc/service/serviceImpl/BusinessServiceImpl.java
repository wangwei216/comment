package org.imooc.service.serviceImpl;

import org.imooc.bean.Business;
import org.imooc.bean.Page;
import org.imooc.constant.CategoryConst;
import org.imooc.dao.BusinessDao;
import org.imooc.dto.BusinessDto;
import org.imooc.dto.BusinessListDto;
import org.imooc.service.BusinessService;
import org.imooc.util.CommonUtil;
import org.imooc.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl  implements BusinessService{

    @Resource
    private BusinessDao businessDao;
    //这个就相当于从系统配置文件中去直接获取商户图片的保存路径
    @Value("${businessImage.savePath}")
    private String savePath;
    //这个是获取图片的请求访问路径从Tomcat发布的路径下
    @Value("${businessImage.url}")
    private String url;

    /*
    * 这个是添加用户实现service方法的实现类
    *   1.先定义一个Business的实体类
    *   2.然后接受（也就是使用spring的工具类）从Controller层中传过来的BusinessDTO中的数据
    *   3.就是判断从Controller层传进来的businessDTO是不是拿到了数据（判断是否为空&&长度大于0）
    *       a.如果成功直接使用文件工具类（这个需要自己创建）把得到的文件流保存到文件名字中
    *       b。把文件名给设置到Business中
    *       c。把相应的采纳数设置进去，调用Dao的方法插入到数据库中，如果插入数据库错误这步需要添加日志信息
    *   4.如果失败直接返回错误信息
    *
    * */
    @Override
    public Boolean add(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);
        if (businessDto!=null && businessDto.getImgFile().getSize()>0){
            try {
                String fileName = FileUtil.save(businessDto.getImgFile(), savePath);
                //然后把图片文件名给set进business专门接受数据的实体类中去
                business.setImgFileName(fileName);
                // 默认已售数量为0
                business.setNumber(5);
                // 默认评论总次数为0
                business.setCommentTotalNum(1L);
                // 默认评论星星总数为0
                business.setStarTotalNum(3L);
                //然后调用dao层
                businessDao.insert(business);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                /*如果到这里插入的时候失败了就需要日志信息进行报错返回控制台*/
                return false;
            }
        }
        else {
            return false;
        }

    }
    //通过商户id获取信息
    @Override
    public BusinessDto getById(long id) {

        BusinessDto result = new BusinessDto();
        Business business = businessDao.selectById(id);
        //然后把从实体里面的数据给复制到数据传输层DTO中
        BeanUtils.copyProperties(business,result);
        //然后把图片路径也给设置进去
        result.setImg(url+business.getImgFileName());
        result.setStar(this.getStar(business));
                 return result;
    }
    /*
     * 通过id查询并且删除广告数据
     *  1.先拿到参数的id去做查询
     *  2.通过id直接去删除
     *  4.通过工具类把图片路径也要删除
     *  3.然后判断影响的行数是不是相等
     * */
    @Override
    public Boolean remove(long id) {
        Business business = businessDao.selectById(id);
        int deleteNum = businessDao.delete(id);
        //这部分是删除商户图片的路径
        FileUtil.delete(savePath+business.getImgFileName());
        return deleteNum==1;
    }

    /*
     * 这个是修改实体信息是不是很成功
     *   1.首先先定一个Ad实体
     *   2.通过spring的工具类把从controller层拿到的数据赋值到这个Ad实体
     *   3.判断从DTO中拿到的数据是不是为空且拿到的文件流长度大于0
     *   4.然后通过自己创建的FileUtil工具类去先把将MultipartFile保存到指定的路径下保存到fileName
     *   5.把得到的文件名给set进要返回的实体类中
     *   6.然后调用dao层的方法到影响到的行数中
     *   7.判断保存的文件名不是为空的话，就删除
     *
     * */
    @Override
    public boolean modify(BusinessDto businessDto) {
        Business business = new Business();
        String fileName = null;
        BeanUtils.copyProperties(businessDto,business);
        if (businessDto.getImgFile()!=null && businessDto.getImgFile().getSize() >0){
            try {
                fileName=  FileUtil.save(businessDto.getImgFile(),savePath);
                business.setImgFileName(fileName);
            } catch (IOException e) {
                //TODO 这个需要添加日志信息
                e.printStackTrace();
                return false;
            }
        }
        int updateNum = businessDao.update(business);
        if (updateNum!=1){
            return false;
        }
        if (fileName!=null){
           return FileUtil.delete(savePath+business.getImgFileName());
        }

        return true;
    }

    /*
    * 通过页面查询条件去分页查询
    *   1.先看返回的是什么类型，就定一个什么类型的list来进行装要返回给controller层的数据
    *   2.然后定义一个实体类型来进行存放我DAO层需要需要穿进去的参数（也就是business实体类型）
    *   3.把从controller层传进来的DTO赋值到存放business的实体类型中
    *   4.然后从DAO去从数据库拿到数据的list穿进去的参数就是第2步创建的那个business实体
    *   5.遍历从dao拿到的数据，然后再去定义临时存放BusinessDTO的实体，把这个实体都add进要最终返回给controller层的集合中
    *   6.最后把遍历的每一个business都赋值给临时存放BusinessDTO中去，最终把第一步定义的list返回
    * */
    @Override
    public List<BusinessDto> searchByPage(BusinessDto businessDto) {
        List<BusinessDto> result = new ArrayList<BusinessDto>();
        //这个用于调用DAO层接口的时候，当做参数穿进去的那个
        Business businessForSelect  = new Business();
        BeanUtils.copyProperties(businessDto,businessForSelect);
        List<Business> list = businessDao.selectByPage(businessForSelect);
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.add(businessDtoTemp);
            BeanUtils.copyProperties(business,businessDtoTemp);

        }

        return result;
    }
    /*
    * 分页查询接口
    * */
    @Override
    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {

        BusinessListDto result = new BusinessListDto();
        // 组织查询条件
        Business businessForSelect = new Business();
        BeanUtils.copyProperties(businessDto, businessForSelect);
        // 当关键字不为空时，把关键字的值分别设置到标题、副标题、描述中
        // TODO 改进做法：全文检索
        if (!CommonUtil.isEmpty(businessDto.getKeyword())) {
            businessForSelect.setTitle(businessDto.getKeyword());
            businessForSelect.setSubtitle(businessDto.getKeyword());
            businessForSelect.setDesc(businessDto.getKeyword());
        }
        // 当类别为全部(all)时，需要将类别清空，不作为过滤条件
        if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            businessForSelect.setCategory(null);
        }
        // 前端app页码从0开始计算，这里需要+1
        int currentPage = businessForSelect.getPage().getCurrentPage();
        businessForSelect.getPage().setCurrentPage(currentPage + 1);

        List<Business> list = businessDao.selectLikeByPage(businessForSelect);

        // 经过查询后根据page对象设置hasMore
        Page page = businessForSelect.getPage();
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());

        // 对查询出的结果进行格式化
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.getData().add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);
            businessDtoTemp.setImg(url + business.getImgFileName());
            // 为兼容前端mumber这个属性
            businessDtoTemp.setMumber(business.getNumber());
            businessDtoTemp.setStar(this.getStar(business));
        }

        return result;
    }


    /*
    * 这个是得到商户星级的信息的一个方法
    * */
    private int getStar(Business business) {
        if(business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (int)(business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0;
        }
    }

}
