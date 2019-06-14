package Algorithms;

/**
 * Created by luoxianzhuo on 2018/8/13 15:16
 *
 * @author luoxianzhuo
 * @copyright Copyright 2014-2018 JD.COM All Right Reserved
 */

import java.util.LinkedList;

/**
 * 155. Min Stack
 * 设计一个支持 push，pop，top 操作，并能在常数时间(O(1))内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * <p>
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class MinStack {

    LinkedList<Integer> linkedList = null;
    LinkedList<Integer> minList = null;

    /**
     * initialize your linkedList structure here.
     */
    public MinStack() {
        this.linkedList = new LinkedList<>();
        this.minList = new LinkedList<>();

    }

    public void push(int x) {
        if (linkedList.size() == 0) {
            linkedList.push(x);
            minList.push(x);
        } else {
            linkedList.push(x);
            if (x < minList.peekFirst()) {
                minList.push(x);
            } else {
                minList.push(minList.peekFirst());
            }
        }
    }

    public void pop() {
        if (linkedList.size() > 0) {
            linkedList.pop();
            minList.pop();
        }
    }

    public int top() {
        if (linkedList.size() > 0) {
            return linkedList.peekFirst();
        }
        return -1;
    }

    public int getMin() {
        if (minList.size() > 0) {
            return minList.peekFirst();
        }
        return -1;

    }

    /**
     * 解法二
     private Stack<Integer> stack;
     private Stack<Integer> minStack;

     public MinStack() {
     stack = new Stack<Integer>();
     minStack = new Stack<Integer>();
     }

     public void push(int x) {
     stack.push(x);
     if (minStack.empty() == true)
     minStack.push(x);
     else {
     // 这里考虑的相等的情况也会继续push
     if (minStack.peek() >= x)
     minStack.push(x);
     }
     }

     public void pop() {
     if (stack.peek().equals(minStack.peek()) ){
     minStack.pop();
     }
     stack.pop();


     }

     public int top() {
     return stack.peek();
     }

     public int getMin() {
     return minStack.peek();
     }



     */


}
