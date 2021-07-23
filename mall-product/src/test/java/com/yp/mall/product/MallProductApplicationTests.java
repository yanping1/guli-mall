package com.yp.mall.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yp.mall.product.dao.AttrGroupDao;
import com.yp.mall.product.dao.SkuSaleAttrValueDao;
import com.yp.mall.product.entity.BrandEntity;
import com.yp.mall.product.service.BrandService;
import com.yp.mall.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

@SpringBootTest
public class MallProductApplicationTests {

    @Autowired
    private OSSClient ossClient;

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private RedissonClient RedissonClient;

    @Resource
    private StringRedisTemplate StringRedisTemplate;

    @Resource
    private AttrGroupDao attrGroupDao;

    @Resource
    private SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Test
    public void testUploud() {
//        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "LTAI5t8vx8dRibXTpmMbPSzd";
//        String accessKeySecret = "6D9mLjIOgdUqIpln4OiqCzSNcH5u1U";
//
//// 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//// 创建PutObjectRequest对象。
//// 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
//// 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//        PutObjectRequest putObjectRequest = new PutObjectRequest("guli-mall-yp", "202104141839415647.jpg", new File("D:\\测试图片\\202104141839415647.jpg"));
//
//// 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
//// ObjectMetadata metadata = new ObjectMetadata();
//// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//// metadata.setObjectAcl(CannedAccessControlList.Private);
//// putObjectRequest.setMetadata(metadata);
//
//// 上传文件。
//        ossClient.putObject(putObjectRequest);
//
//// 关闭OSSClient。
//        ossClient.shutdown();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:\\测试图片\\jugg.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject("guli-mall-yp", "jugg.png", inputStream);
        //// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testRedisson() {
        System.out.println(RedissonClient);
    }

    @Test
    public void attrGroupTest() {
        System.out.println(attrGroupDao.getAttrGroupWithAttrsBySpuId(225L, 3L));
    }

    @Test
    public void SkuSaleAttrValueTest() {
        System.out.println(skuSaleAttrValueDao.getSaleAttrsBuSpuId(3L));
    }

    @Test
    public void testRedis() {
        // 写入 redis
        ValueOperations<String, String> operations = StringRedisTemplate.opsForValue();
        operations.set("redis", "firenay");

        // 读取redis值
        String redis = operations.get("redis");
        System.out.println("读取到的类型：" + redis);
    }

    /**
     * 查找父节点的路径
     */
    @Test
    public void testFindPath() {
        Long[] paths = categoryService.findCateLogPath(225L);
        System.out.println(Arrays.asList(paths));
    }

    @Test
    public void testBrand() {
        BrandEntity brandEntity = new BrandEntity();
//		brandEntity.setDescript("test");
//		brandEntity.setName("华为");
//		if(brandService.save(brandEntity)){
//			System.out.println("保存成功");
//		}
//		brandEntity.setBrandId(1L);
//		brandEntity.setLogo("华为");
//		brandService.updateById(brandEntity);

        brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L)).forEach(System.out::println);
    }

}
