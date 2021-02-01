package com.user.util.schema.practice;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class LockDemo {

    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "bbb";
        AtomicReference<String> reference = new AtomicReference<String>(str1);

        System.out.println(reference.get().equals(str1));
        System.out.println(reference.get().equals(str2));

        reference.compareAndSet(str2,str2);
        System.out.println(reference.get());

        AtomicStampedReference<String> ref = new AtomicStampedReference<String>(str1,1);
        ref.compareAndSet(str1,str2,ref.getStamp(),ref.getStamp()+1);
        System.out.println("reference.getReference() = " + ref.getReference());





        boolean b = ref.attemptStamp(str2, ref.getStamp() + 1);
        System.out.println("b: "+b);
        System.out.println("reference.getStamp() = "+ref.getStamp());

        boolean c = ref.weakCompareAndSet(str2,"ccc",1, ref.getStamp()+1);
        System.out.println("reference.getReference() = "+ref.getReference());
        System.out.println("c = " + c);
    }
}
