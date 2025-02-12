package org.swyp.weddy.domain.smallcategory.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.smallcategory.service.SmallCatService;
import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.smallcategory.web.request.SmallCatItemPostRequest;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.smallcategory.web.response.SmallCatItemResponse;

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
    public ResponseEntity<List<SmallCatItemPreviewResponse>> getSmallCatItemList(@RequestParam(name = "checkListId") Long checkListId,
                                                                    @RequestParam(name = "largeCatItemId") Long largeCatItemId) {
        List<SmallCatItemPreviewResponse> itemPreviews = smallCatService.findItemPreviews(checkListId, largeCatItemId);
        return ResponseEntity.ok().body(itemPreviews);
    }

    @GetMapping("/item")
    public ResponseEntity<SmallCatItemResponse> getSmallCatItem(@RequestParam(name = "checkListId") Long checkListId,
                                                                @RequestParam(name = "largeCatItemId") Long largeCatItemId,
                                                                @RequestParam(name = "smallCatItemId") Long smallCatItemId) {
        SmallCatItemResponse item = smallCatService.findItem(checkListId, largeCatItemId, smallCatItemId);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/add-item")
    public ResponseEntity<Long> postItem(@RequestBody SmallCatItemPostRequest smallCatItemPostRequest) {
        SmallCatItemDto smallCatItemDto = SmallCatItemDto.from(smallCatItemPostRequest);
        Long itemId = smallCatService.addItem(smallCatItemDto);
        return ResponseEntity.ok().body(itemId);
    }


}
