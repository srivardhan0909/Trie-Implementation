import java.util.*;

public class Code{
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        Trie root = new Trie();
        
        while(n--> 0){
            int op = scn.nextInt();
            if(op==1){
                String s = scn.next();
                insert(root,s);
                System.out.println("The word " + s + " inserted successfully");
            }
            else if(op==2){
                String s = scn.next();
                if(doesExists(root,s))
                System.out.println("The word exists");
                else 
                System.out.println("The word does not exist");
            }
            else if(op==3){
                List<String> li = new ArrayList<>();
                help(root,li,"");
                System.out.println(li);
            }
            else if(op==4){
                String ps = scn.next();
                List<String> li = new ArrayList<>();
                Trie te = root;
                boolean prefixExists = true;
                
                for(char ci : ps.toCharArray()){
                    int ind = ci-'a';
                    if(te.ch[ind]==null){
                        prefixExists = false;
                        break;
                    }
                    te=te.ch[ind];
                }
                
                if (prefixExists) {
                    help(te, li, ps);
                    System.out.println(li);
                } else {
                    System.out.println("No words found with prefix: " + ps);
                }
            }
        }
        scn.close();
    }

    static void insert(Trie root , String s){
        Trie te = root;
        for(char ci : s.toCharArray()){
            int ind = ci-'a';
            if(te.ch[ind]==null){
                te.ch[ind] = new Trie();
            }
            te=te.ch[ind];
            te.wc++;
        }
        te.ended = true;
    }

    static boolean doesExists(Trie root , String s){
        Trie te = root;
        for(char ci : s.toCharArray()){
            int ind = ci-'a';
            if(te.ch[ind]==null){
                return false;
            }
            te=te.ch[ind];
        }
        return te.ended;
    }

    static void help(Trie root , List<String> li , String te){
        if(root.ended)
        li.add(te);
        for(int i=0;i<26;i++){
            if(root.ch[i]!=null){
                char ch = (char)(i+'a');
                help(root.ch[i],li,te+ch);
            }
        }
    }
}

class Trie{
    Trie ch[];
    int wc ;
    boolean ended;
    Trie(){
        ch = new Trie[26];
        ended = false;
        wc = 0;
    }
}