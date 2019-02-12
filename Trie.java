package Trie;

public class Trie {
    private TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }

    public void insert(String word){
        TrieNode dup = this.find(word);
        if(null != dup && dup.isEnd){
            dup.same++;
            int ind = dup.same;
//            System.out.println("index is "+ind);
            char[] dupCount = String.valueOf(ind).toCharArray();
            for(int i=0;i<dupCount.length;i++){
                ind = ('z' - 'a') + 1 + Integer.parseInt(dupCount[i]+"");
//                System.out.println("inside loop "+ind);
                dup.children[ind] = new TrieNode();
                dup.count++;
                dup = dup.children[ind];
            }
            dup.count++;
            dup.isEnd = true;

        }else{
            char[] chars = word.toCharArray();
            TrieNode curr = root;
            for(int i=0;i<chars.length;i++){
                if(null == curr.children[chars[i] - 'a']){
                    curr.children[chars[i] - 'a'] = new TrieNode();
                }
                curr.count++;
                curr = curr.children[chars[i]-'a'];
            }
            curr.count++;
            curr.isEnd = true;
        }
        this.PrintWord(word);
    }

    public TrieNode find(String word){
        TrieNode curr = this.root;
        char[] chars = word.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(null == curr.children[chars[i] - 'a']){
                return null;
            }
            curr = curr.children[chars[i] - 'a'];
        }
        return curr;
    }

    public boolean search(String word){
        TrieNode curr = this.find(word);
        if(null == curr){
            return false;
        }else{
            return curr.isEnd;
        }
    }

    public boolean startsWith(String prefix){
        TrieNode curr = this.find(prefix);
        if(null != curr){
            return true;
        }
        return false;
    }

    public int countPrefix(String prefix){
        TrieNode curr = this.find(prefix);
        if(null == curr){
            return 0;
        }
        return curr.count;
    }

    public void PrintWord(String word){
        TrieNode wrd = this.find(word);
        StringBuilder strb = new StringBuilder();
        if(wrd.isEnd && wrd.same == -1){
            System.out.println(word);
        }else if(wrd.isEnd && wrd.same > -1){
            char[] offset = String.valueOf(wrd.same).toCharArray();
            for(int i=0;i<offset.length;i++){
                int ind = Integer.parseInt(offset[i]+"")+1+('z'-'a');
                if(null != wrd.children[ind]){
                    strb.append(String.valueOf(offset[i]));
                    wrd = wrd.children[ind];

                }
            }
//            System.out.println(wrd.isEnd);
            if(wrd.isEnd){
                System.out.println(word+strb);
            }
        }
    }

    public String getPrefix(String word){
        TrieNode curr = this.root;
        StringBuilder str = new StringBuilder();
        char[] chars = word.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(null == curr.children[chars[i] - 'a']){
                if(curr.isEnd){
                    return str.toString();
                }else{
                    return word;
                }
            }
            str.append(chars[i]);
            curr = curr.children[chars[i] - 'a'];
        }
        return str.toString();
    }

}
