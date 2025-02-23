package org.swyp.weddy.domain.checklist.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.checklist.service.SmallCatService;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemDto;
import org.swyp.weddy.domain.checklist.service.dto.SmallCatItemMoveDto;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemMoveRequest;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemPatchRequest;
import org.swyp.weddy.domain.checklist.web.request.SmallCatItemPostRequest;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemPreviewResponse;
import org.swyp.weddy.domain.checklist.web.response.SmallCatItemResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("checklist/large-cat-item/small-cat-Item")
public class SmallCatController {

    private final SmallCatService smallCatService;

    public SmallCatController(SmallCatService smallCatService) {
        this.smallCatService = smallCatService;
    }

    @GetMapping("/item-previews")
    public ResponseEntity<List<SmallCatItemPreviewResponse>> getSmallCatItemList(@RequestParam(name = "checklistId") Long checklistId,
                                                                                 @RequestParam(name = "largeCatItemId") Long largeCatItemId) {
        List<SmallCatItemPreviewResponse> itemPreviews = smallCatService.findItemPreviews(checklistId, largeCatItemId);

        return ResponseEntity.ok().body(itemPreviews);
    }

    @GetMapping("/item")
    public ResponseEntity<SmallCatItemResponse> getSmallCatItem(@RequestParam(name = "checklistId") Long checklistId,
                                                                @RequestParam(name = "largeCatItemId") Long largeCatItemId,
                                                                @RequestParam(name = "smallCatItemId") Long smallCatItemId) {

        SmallCatItemResponse item = smallCatService.findItem(checklistId, largeCatItemId, smallCatItemId);

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
    public ResponseEntity<Boolean> deleteItem(@RequestParam(name = "checklistId") Long checklistId,
                                              @RequestParam(name = "largeCatItemId") Long largeCatItemId,
                                              @RequestParam(name = "smallCatItemId") Long smallCatItemId) {

        boolean result = smallCatService.deleteItem(checklistId, largeCatItemId, smallCatItemId);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/delete-all-items")
    public ResponseEntity<Boolean> deleteAllItems(@RequestParam(name = "checklistId") Long checklistId,
                                                  @RequestParam(name = "largeCatItemId") Long largeCatItemId) {

        boolean result = smallCatService.deleteAll(checklistId, largeCatItemId);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/move-item")
    public ResponseEntity<Boolean> moveItem(@RequestBody SmallCatItemMoveRequest request) {

        SmallCatItemMoveDto dto = SmallCatItemMoveDto.from(request);
        boolean result = smallCatService.moveItem(dto);

        return ResponseEntity.ok().body(result);
    }

}
