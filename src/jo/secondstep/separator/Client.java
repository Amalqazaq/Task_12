package jo.secondstep.separator;

import java.util.ArrayList;
import java.util.List;

public class Client {
public static void main(String[] args) {
	Separator separator=new Separator();
	List<Integer> list=new ArrayList<>();
	list.add(2);
	list.add(7);
	list.add(1);
	list.add(4);
	list.add(5);
	list.add(8);
	list.add(3);
	list.add(6);
	separator.separate(list);
}
}
