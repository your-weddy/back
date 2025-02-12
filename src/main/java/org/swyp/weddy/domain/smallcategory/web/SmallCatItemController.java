package org.swyp.weddy.domain.smallcategory.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.swyp.weddy.domain.smallcategory.service.SmallCatService;
import org.swyp.weddy.domain.smallcategory.web.res.SmallCatItemPreviewResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("checklist/large-cat-item/small-cat-Item")
public class SmallCatItemController {

    private final SmallCatService smallCatService;

    public SmallCatItemController(SmallCatService smallCatService) {
        this.smallCatService = smallCatService;
    }

    @GetMapping("/item-previews")
    public ResponseEntity<List<SmallCatItemPreviewResponse>> getSmallCatItemList(@RequestParam(name = "checkListId") String checkListId,
                                                                    @RequestParam(name = "largeCatItemId") String largeCatItemId) {
        List<SmallCatItemPreviewResponse> itemPreviews = smallCatService.findItemPreviews(Long.valueOf(checkListId), Long.valueOf(largeCatItemId));
        return ResponseEntity.ok().body(itemPreviews);
    }

}
