package org.swyp.weddy.domain.smallcategory.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.smallcategory.service.SmallCatService;
import org.swyp.weddy.domain.smallcategory.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.smallcategory.web.request.SmallCatItemPatchRequest;
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
    public ResponseEntity<Long> postItem(@RequestBody SmallCatItemPostRequest request) {

        SmallCatItemDto smallCatItemDto = SmallCatItemDto.from(request);
        Long itemId = smallCatService.addItem(smallCatItemDto);

        return ResponseEntity.ok().body(itemId);
    }

    @PatchMapping("/update-item")
    public ResponseEntity<Boolean> patchItem(@RequestBody SmallCatItemPatchRequest request) {

        SmallCatItemDto smallCatItemDto = SmallCatItemDto.from(request);
        boolean result = smallCatService.editItem(smallCatItemDto);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/delete-item")
    public ResponseEntity<Boolean> deleteItem(@RequestParam(name = "checkListId") Long checkListId,
                                              @RequestParam(name = "largeCatItemId") Long largeCatItemId,
                                              @RequestParam(name = "smallCatItemId") Long smallCatItemId) {

        boolean result = smallCatService.deleteItem(checkListId, largeCatItemId, smallCatItemId);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/delete-all-items")
    public ResponseEntity<Boolean> deleteAllItems(@RequestParam(name = "checkListId") Long checkListId,
                                                  @RequestParam(name = "largeCatItemId") Long largeCatItemId) {

        boolean result = smallCatService.deleteAll(checkListId, largeCatItemId);

        return ResponseEntity.ok().body(result);
    }




}
