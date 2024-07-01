package com.gradescope.assignment1;

import java.util.EmptyStackException;

import com.gradescope.assignment1.AbstractDemoStack;

public class DemoStack extends AbstractDemoStack{
    Character[] base;
    int size = 0;
        public DemoStack() {
            super();
            base = new Character[10];
            int size = 0;
        }

        public void push(Character i) {
            /*
             * To be filled in by the student
             * Input: Character to be inserted onto top of stack
             */
            int len = base.length;
            if (size == len) {
                Character[] arr = base;
                len = 2 * len;
                base = new Character[len];   // defining the base array
                for (int j = 0; j < size; j++)
                    base[j] = arr[j];
            }
            base[size] = i;
            size = size + 1;
        }

        public Character pop() throws EmptyStackException {
            /*
             * To be filled in by the student
             * Return: Character present at the top of the stack
             */
            if(size>0) {
                int len = 40;
                char value = base[size - 1];   // defining variable for value on top
                size = size - 1;
                if (size < len / 4 && len > 40) {
                    Character[] arr = base;
                    len = len / 2;
                    base = new Character[len];
                    for (int i = 0; i < size; i++)
                        base[i] = arr[i];
                }
                return value;
            }
            else{
                throw new EmptyStackException();
            }
        }

        public Boolean is_empty() {
            /*
             * To be filled in by the student
             * Return: Stack is empty or not
             */
            if (size == 0) {
                return true;
            } else {
                return false;
            }
        }


        public Integer size() {
            /*
             * To be filled in by the student
             * Return: Number of elements which are present in stack
             */
            return size;
        }

        public Character top() throws EmptyStackException {
            /*
             * To be filled in by the student
             * Return: Character present at the top of the stack
             */
            if(size>0) {
                return base[size - 1];
            }
            else{
                throw new EmptyStackException();
            }
        }

        public Character[] return_base_array() {
            /*
             * To be filled in the by the student
             * Return: Return reference to the base array storing the elements of stack
             */
            Character[] NewArr = new Character[size];
            for (int i = 0; i < size; i++) {
                NewArr[i] = base[i];

            }
            return NewArr;
        }

    }




