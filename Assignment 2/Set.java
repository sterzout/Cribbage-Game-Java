public class Set <T> {
    private LinearNode<T> setStart;
//  creating private instance variable of type LinearNode <T> which will hold our first reference of the first node.

    public Set() {
        setStart = null;
//  set setStart equal to null
    }

    public void add(T element) {
        LinearNode<T> node1 = new LinearNode<T>(element);
        if (setStart == null) {
            setStart = node1;
//  sets node to null if setStart is null
        } else {
            LinearNode<T> curr = setStart;
            while (curr.getNext() != null) {
                curr = curr.getNext();
//  else current node will retrieve next and check if it is null too
            }
            curr.setNext(node1);
//  when current becomes null we set what we have to node1
        }
    }

    public int getLength() {
        int counter = 0;
//  counter used for keep track of current node
        LinearNode<T> curr = setStart;
//  set curr to setStart (null)
        while (curr != null) {
            curr = curr.getNext();
            counter++;
//  while not null add 1 to counter
        }
        return counter;
//  return counter which is length
    }

    public T getElement(int i) {
        LinearNode<T> curr = setStart;
        //  set curr to setStart (null)
        int j = 0;
        if (curr == null){
            return null;
//      return null if curr is empty
        }else{
            for (int k = 0; k<i; k++){
                curr= curr.getNext();
//  keep returning next curr until we hit i
            }
            return curr.getElement();
//  once is hit we return that element at i
        }
    }
    public boolean contains(T element){
        LinearNode<T> curr = setStart;
        while (curr != null){
            if (curr.getElement().equals(element)){
                return true;
//  if curr element is element (in the parameter) then we return true
            }
            curr = curr.getNext();
//  otherwise keep returning curr.getnext
       }
        return false;
//  return false if it is not found
    }
    public String toString(){
        LinearNode<T> curr = setStart;
//  set curr to setStart (null)

        if (curr == null){
            return "";
//  return empty string if its empty
        }
        StringBuilder nodeT = new StringBuilder();
//  creating our stringBuilder
        while (curr != null){
            nodeT.append(curr.getElement()).append(" ");
            curr = curr.getNext();
//  add element plus space to our string builder until we reach null node
        }
        return nodeT.toString();
//  return our nodeT toString
    }
}