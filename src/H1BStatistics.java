import java.util.*;
import java.io.*;
class H1BStatistics{
    public static Map<Integer,List<String>> sortByValues(Map<String,Integer> map){
        Map<Integer,List<String>> u=new TreeMap<>(Collections.reverseOrder());
        for(Map.Entry<String,Integer> m:map.entrySet()){
            if(u.containsKey(m.getValue())){
                u.get(m.getValue()).add(m.getKey());
            }else{
                List<String> a=new ArrayList<>();
                a.add(m.getKey());
                u.put(m.getValue(),a);
            }
        }
        return u;
    }
    public static void main(String args[]) throws Exception{
        int total_count=0;
        Map<String,Integer> occ=new TreeMap<>();
        Map<String,Integer> states=new TreeMap<>();
        BufferedReader br=new BufferedReader(new FileReader(new File(args[0])));
        String line=br.readLine();
        while((line=br.readLine())!=null){
            String[] temp=line.replace("\"","").split(";");
            if(temp[2].equals("CERTIFIED")){
                occ.put(temp[24],occ.containsKey(temp[24])?occ.get(temp[24])+1:1);
                states.put(temp[50],states.containsKey(temp[50])?states.get(temp[50])+1:1);
                total_count++;
            }
        }

        String s="TOP_OCCUPATIONS;NUMBER_CERTIFIED_APPLICATIONS;PERCENTAGE\n";
        Map<Integer,List<String>> u=sortByValues(occ);
        for(Map.Entry<Integer,List<String>> m:u.entrySet()){
            double val=((double)m.getKey()*100)/(double)total_count;
            for(String i:m.getValue())
                s=s+i+";"+m.getKey()+";"+val+"%\n";
        }

        u=sortByValues(states);
        String s1="TOP_STATES;NUMBER_CERTIFIED_APPLICATIONS;PERCENTAGE\n";
        for(Map.Entry<Integer,List<String>> m:u.entrySet()){
            double val=((double)m.getKey()*100)/(double)total_count;
            for(String i:m.getValue())
                s1=s1+i+";"+m.getKey()+";"+val+"%\n";
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
		writer.write(s);
		writer.close();
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(args[2]));
		writer1.write(s1);
		writer1.close();
    }
}
//CERT - 2 , JOB - 24 , State - 50
