// package com.isha;

import java.util.LinkedList;
//import java.util.Iterator;

import Includes.DictionaryEntry;
import Includes.HashTableEntry;
import Includes.KeyAlreadyExistException;
import Includes.KeyNotFoundException;
import Includes.NullKeyException;

import java.lang.reflect.Array;

public class COL106Dictionary<K, V> {
    private int size = 0;


    private LinkedList<DictionaryEntry<K, V>> dict;
    /*
     * dict is a Linked-List, where every node of linked-list is of type DictionaryEntry.
     * DictionaryEntry is a key-value pair, where the type of key and value is K and V respectively.
     */ 
    public LinkedList<HashTableEntry<K, V>>[] hashTable;
    /*
     * hashTable is an array of Linked-Lists which is initialized by the COL106Dictionary constructor.
     * Each index of hashTable stores a linked-list whose nodes are of type HashTableEntry
     * HashTableEntry is a key-address pair, where the type of key is K and the corresponding address is the address of the DictionaryEntry in the linked-list corresponding to the key of HashTableEntry
     */ 
    
    @SuppressWarnings("unchecked")
    COL106Dictionary(int hashTableSize) {
        dict = new LinkedList<DictionaryEntry<K, V>>();
        // This statement initiailizes a linked-list where each node is of type DictionaryEntry with key and value of type K and V respectively.
        hashTable = (LinkedList<HashTableEntry<K, V>>[]) Array.newInstance(LinkedList.class, hashTableSize);
        // This statement initiailizes the hashTable with an array of size hashTableSize where at each index the element is an instance of LinkedList class and
        // this array is type-casted to an array of LinkedList where the LinkedList contains nodes of type HashTableEntry with key of type K. 
    }

    public void insert(K key, V value) throws KeyAlreadyExistException, NullKeyException {
        /*
         * To be filled in by the student
         * Input: A key of type K and it corresponding value of type V
         * Working: Inserts the argumented key-value pair in the Dictionary in O(1)
         */
        if (key == null) {
            throw new NullKeyException();
        }
        int index = hash(key);

        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
        }
        else{
            throw new KeyAlreadyExistException();
        }
        LinkedList<HashTableEntry<K, V>> list = hashTable[index];
        DictionaryEntry<K, V> dictent = new DictionaryEntry<>(key, value);
        dict.add(dictent);
        HashTableEntry<K, V> newEntry = new HashTableEntry<>(key, dictent);
        list.add(newEntry);
        // size++;

    }

    public V delete(K key) throws NullKeyException, KeyNotFoundException{
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key
         * Working: Deletes the key-value pair from the Dictionary in O(1)
         */
        if (key == null) {
            throw new NullKeyException();
        }
        int index = hash(key);
        LinkedList<HashTableEntry<K, V>> list = hashTable[index];
//        Iterator<HashTableEntry<K, V>> iterator = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            HashTableEntry<K, V> entry = list.get(i);
            if (entry.key.equals(key)) {
                V value = entry.dictEntry.value;
                list.remove(i);
                dict.remove(entry.dictEntry);
                // size--;
                return value;
            }
            else{
                throw new KeyNotFoundException();
            }
        }

        return dict.getFirst().value;
    }

    public int update(K key, V value) throws NullKeyException, KeyNotFoundException {
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the previously associated value of type V with the argumented key
         * Working: Updates the value associated with argumented key with the argumented value in O(1)
         */
        if (key == null) {
            throw new NullKeyException();
        }
        int index = hash(key);
        LinkedList<HashTableEntry<K, V>> list = hashTable[index];
        for (int i = 0; i < list.size(); i++) {
            HashTableEntry<K, V> entry = list.get(i);
            if (entry.key.equals(key)) {
                V oldValue = entry.dictEntry.value;
                entry.dictEntry.value = value;
                return (int)oldValue;
            }
            else{
                throw new KeyNotFoundException();
            }
        }

        return (int)dict.getFirst().value;
    }


    public V get(K key) throws NullKeyException, KeyNotFoundException {
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key in O(1)
         */
        if (key == null) {
            throw new NullKeyException();
        }
        int index = hash(key);
        LinkedList<HashTableEntry<K, V>> list = hashTable[index];
        for (HashTableEntry<K, V> entry : list) {
            if (entry.key.equals(key)) {
                return entry.dictEntry.value;
            }
            else{
                throw new KeyNotFoundException();
            }
        }
        return dict.getFirst().value;
    }

    public int size() {
        /*
         * To be filled in by the student
         * Return: Returns the size of the Dictionary in O(1)
         */
        return dict.size();
    }

    @SuppressWarnings("unchecked")
    public K[] keys(Class<K> cls) {
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */

        int size = dict.size();
        K[] keysArray = (K[]) Array.newInstance(dict.getFirst().key.getClass(), size);

        int index = 0;
        for (DictionaryEntry<K, V> entry : dict) {
            keysArray[index++] = entry.key;
        }

        return keysArray;
    }

    @SuppressWarnings("unchecked")
    public V[] values(Class<V> cls) {
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */

        V[] values = (V[]) new Object[dict.size()];
        // Iterate through the linked list of dictionary entries
        int i = 0;
        for (DictionaryEntry<K, V> entry : dict) {
            // Add the value of each entry to the values array
            values[i++] = entry.value;
        }
        return values;
    }

    public int hash(K key) {
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the hash of the argumented key using the Polynomial Rolling
         * Hash Function.
         */
        int p = 131, m = hashTable.length;
//        System.out.println(m);
        int h = 0;
        String s = key.toString();
//        System.out.println((int)("a"));
        int p1 = 1;
        for (int i = 0; i < s.length(); i++) {
            h = (h * p + (s.charAt(s.length() - i - 1) + 1)) % m;
        }
        return h;
    }

//    public static void main(String args[]) throws Exception{
//        COL106Dictionary d = new COL106Dictionary(20);
//        d.insert("Delhi",20);
//        d.insert("Karnataka", 40);
//        System.out.println(d.update("Delhi",30));
//
//    }
}
