package com.dxg.msns.dynamic.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.DynamicType;
import com.dxg.msns.dynamic.service.DynamicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("category")
public class DynamicTypeController {
    @Autowired
    private DynamicTypeService dynamicTypeService;

    /**
     * 查询所有分类
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<DynamicType>> queryDynamicTypesByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy[],
            @RequestParam(value = "desc",required = false)Boolean desc[],
            @RequestParam(value = "isAll",required = false)Boolean isAll
    ){
        if (page<0 ||rows<0){
            return ResponseEntity.badRequest().build();
        }
        PageResult<DynamicType> result = this.dynamicTypeService.queryDynamicTypesByPage(key,page,rows,sortBy,desc,isAll);

        return ResponseEntity.ok(result);
    }

    /**
     * 新增分类
     * @param dynamicType
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addType(@RequestBody DynamicType dynamicType){
//        System.out.println("controller::::::"+dynamicType);
        if (dynamicType.getTypeStatusBoolean()){
            dynamicType.setTypeStatus("1");
        }else {
            dynamicType.setTypeStatus("0");
        }
        this.dynamicTypeService.addType(dynamicType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改
     * @param dynamicType
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateType(@RequestBody DynamicType dynamicType){
//        System.out.println("controller::::::"+dynamicType);
        if (!dynamicType.getTypeStatus().equals("-1")){
            if (dynamicType.getTypeStatusBoolean()){
                dynamicType.setTypeStatus("1");
            }else {
                dynamicType.setTypeStatus("0");
            }
        }
//        System.out.println("controller::::::"+dynamicType);
        this.dynamicTypeService.updateType(dynamicType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 删除
     * @param dynamicType
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteType(@RequestBody DynamicType dynamicType){

//        System.out.println("controller::::::"+dynamicType);
        this.dynamicTypeService.deleteType(dynamicType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}