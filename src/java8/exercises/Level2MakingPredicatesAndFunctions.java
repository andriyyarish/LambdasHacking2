import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by olexandra on 2/25/16.
 */
public class Level2MakingPredicatesAndFunctions {

    List<Integer> numbers = Arrays.asList(2, 8, 5, 1, 6, 4,9, 7, 8, 4, 0); //9 deleted from list


    /* Exercise 1
    Given: List of more than 10 integers. Using streams filter even values and output the sum of 3 -5 elements.
     */
    @Test

    public void SumOf3Dash5Elemnts() {

        //given
        //TODO: write your implementation instead of nulls and 0
        Predicate<Integer> getOdd = num -> num%2 == 0;

        //whena
        List<Integer> odd2To5Values = numbers.stream().
                filter(getOdd).
                skip(2).limit(3).
                collect(Collectors.toList());

        long sumOfIntStream = odd2To5Values.stream().mapToInt(n -> n).sum();

        LongSummaryStatistics sumStat = odd2To5Values.stream().mapToLong(n -> n).summaryStatistics();
        long sumSummarizing = sumStat.getSum() ;

        long sumSumming = odd2To5Values.stream().collect(Collectors.summingInt(n ->n));

        long sumReduceOwn = odd2To5Values.stream().reduce(0,((a,b) -> a+b));

        long sumReduceInteger = odd2To5Values.stream().reduce(Integer::sum).get();

        //then
        assertEquals(Arrays.asList(6, 4, 8), odd2To5Values);
        assertEquals(18, sumOfIntStream);
        assertEquals(18, sumSummarizing);
        assertEquals(18, sumSumming);
        assertEquals(18, sumReduceOwn);
        assertEquals(18, sumReduceInteger);

    }

    /* Exercise 2
    Given: List of integers. Using streams filter odd values and output the maximal one.
    1) using stream max
    2) using IntStream max
    3) using stream reduce + Integer max function
    4) using Collectors max
    5) using Collectors reducing + Integer max function
    6) using Collectors summarizing
 */

//odd - neparni  even - parni
    @Test

    public void filterOddMax() {

        //TODO: write your implementation instead of nulls and 0

        //given
        Predicate<Integer> getOdd = n -> n%2!=0;
        //Stream<Integer> intOdStream = numbers.stream().filter(getOdd);
        //when
        //1)
        int maxInStream = numbers.stream().filter(getOdd).max((a,b) -> a.compareTo(b)).get();

        //2)
        int maxInIntStream = numbers.stream().filter(getOdd).mapToInt(n ->n).max().getAsInt();

        //3.1)
        int maxUsingReduce = numbers.stream().filter(getOdd).reduce(0,(a,b) -> a.compareTo(b)).intValue();

        //3.2)
        int maxUsingCollectorsReduceOwn = 0;

        //4)
        int maxUsingComparatorMax = 0;

        //5)
        int maxUsingCollectorsReduce = 0;

        //6)
        IntSummaryStatistics intStat = numbers.stream().filter(getOdd).mapToInt(n->n).summaryStatistics();
        int maxUsingCollectorsSummarizing = intStat.getMax();

        System.out.println(" maxInIntStream = " + maxInIntStream + " maxInIntStream = "+ maxInIntStream );
        System.out.println( " maxUsingReduce = " + maxUsingReduce + " maxUsingCollectorsReduceOwn" + maxUsingCollectorsReduceOwn);
        System.out.println("maxUsingComparatorMax = " +  maxUsingComparatorMax +
                " maxUsingCollectorsReduce = " + maxUsingCollectorsReduce + " maxUsingCollectorsSummarizing = "
                +maxUsingCollectorsSummarizing);
        //then
        assertEquals(8, maxInStream);
        assertEquals(8, maxInIntStream);
        assertEquals(8, maxUsingReduce);
        assertEquals(8, maxUsingCollectorsReduceOwn);
        assertEquals(8, maxUsingComparatorMax);
        assertEquals(8, maxUsingCollectorsReduce);
        assertEquals(8, maxUsingCollectorsSummarizing);

    }

    /* Exercise 3
            Write the function which will compare (gt) current int of the stream with the input int and return predicate:
         Function<Integer, Predicate<Integer>> compareNumbers.
        */

    @Test
    @Ignore
    public void makeFunction() {

        //given
        final int intToCompare = 4;
        Predicate<Integer> predInt = s -> s>intToCompare;
        System.out.println(predInt.test(3));
//        Function<Integer, Predicate<Integer>> getGreaterThan = (n,predicate) ->
//                            { if(predInt.test(n)) return new Integer(n) ;};

        //when
        List<Integer> actual = numbers.stream().filter(s->predInt.test(s)).collect(Collectors.toList());

        //then
        assertEquals(Arrays.asList(8, 5, 6, 9, 7, 8), actual);

    }
}
