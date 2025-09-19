package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository를 사용하면 component의 대상이 됨
@Repository
public class ItemRepository {
    /**
     * 멀티 스레드 환경에서 여러 개가 동시에 store에 접근하게되면 hashMap쓰면 안됨 -> 싱글톤
     * 사용하고 싶으면 ConcurrentHashMap을 사용해야 함
     */
    private static final Map<Long, Item> store = new HashMap<>(); //static(실제로는 HashMap안쓰는게 좋음)
    private static long sequence=0L; //static

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
