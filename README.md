primefactors
============

Clean Coders Episode 19 Advanced TDD Part 2 Examples

Incremental Algorithmics

TDD의 비밀: “As the tests get more specific, the code gets more generic.”
let the tests drive me to a solution, rather than impose a solution on the tests.

# 0. 용어 정의
## 소수(prime number)
1과 자기 자신 외의 정수로는 나누어 떨어지지 않는 정수.
예.  2, 3, 5, 7, 11, 13, 17, 19, ....

## 소인수(prime factor)
어떤 정수를 소수만의 곱으로 나타낼 때 그 인수가 되는 각각의 소수. ‘30=2×3×5’에서 ‘2’, ‘3’, ‘5’ 따위의 소수를 말한다.

# 1. nothing
늘 그렇듯 `public void nothing`으로 시작

```
public class PrimeFactorsTest {
    @Test public void
    nothing() {
    }
}
```
# 2. canFactorIntoPrimes for 1
## 2.1 add failing test
가장 단순한 1에 대한 소인수를 구하는 테스트를 추가한다.

```
@Test public void
canFactorIntoPrimes() {
    assertEquals(Arrays.asList(), of(1));
}
```
## 2.2 make it pass

```
private List<Integer> of(int n) {
    return Arrays.asList();
}
```
## 2.3 Refactor
`'assertEquals()' between objects of inconvertible types 'List<Object>' and 'List<Integer>'` 경고 메시지를 제거하기 위해 assertEquals의 Arrays.asList()를 list()로 추출

```
@Test
public void
canFactorIntoPrimes() {
    assertEquals(list(), of(1));
}

private List<Integer> list() {
    return Arrays.asList();
}
```
# 3. canFactorIntoPrimes for 2

## 3.1 add failing test

```
@Test public void
canFactorIntoPrimes() {
    assertEquals(list(), of(1));
    assertEquals(list(2), of(2));
}
```
## 3.2 make it pass

```
private List<Integer> list(Integer... ints) {
    return Arrays.asList(ints);
}

private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    if(n == 2)
        return Arrays.asList(2);
    return factors;
}
```
## 3.3 refactor
if(n == 2)보다는 if(n > 1)이 보다 general하다. 보다 general하도록 수정한다.

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    if(n > 1)
        return Arrays.asList(2);
    return factors;
}
```
# 4. canFactorIntoPrimes for 3
## 4.1 add failing test

```
@Test public void
canFactorIntoPrimes() {
    assertEquals(list(), of(1));
    assertEquals(list(2), of(2));
    assertEquals(list(3), of(3));
}
```
## 4.2 make it pass

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    if(n > 1)
        return Arrays.asList(n);
    return factors;
}
````
## 4.3 refactor
### extract method assertPrimeFactors
assertPrimeFactors를 extract method
이때도 3개의 라인에 모두 적용되도록 하기 위해

- extract variable
  - list()를 primeFactors로
  - 1을 n으로
- extract method assertPrimeFactors
- inline variable

### change signature

`assertPrimeFactors(list(), 1);`보다는 `assertPrimeFactors(1, list());`이 보가 좋다. change signature refactoring을 통해서 파라미터 순서를 변경한다.

```
@Test public void
canFactorIntoPrimes() {
    assertPrimeFactors(1, list());
    assertPrimeFactors(2, list(2));
    assertPrimeFactors(3, list(3));
}

private void assertPrimeFactors(int n, List<Integer> primeFactors) {
    assertEquals(primeFactors, of(n));
}
```
훨씬 읽기 쉬워졌다.
# 5. canFactorIntoPrimes for 4
## 5.1 add failing test

```
@Test
public void
canFactorIntoPrimes() {
    assertPrimeFactors(1, list());
    assertPrimeFactors(2, list(2));
    assertPrimeFactors(3, list(3));
    assertPrimeFactors(4, list(2, 2));
}
```

## 5.2 make it pass

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    if (n > 1) {
        if(n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }
        if(n > 1)
            factors.add(n);
    }
    return factors;
}
```

## 5.3 refactor

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    if (n > 1) {
        if (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }
    }
    if (n > 1)
        factors.add(n);
    return factors;
}
```
서로 성격이 다른 

- n을 factors에 추가하는 코드블록과
- % 2 == 0인 코드블록

을 분리하고 테스트가 성공하는지 확인한다.
# 6. canFactorIntoPrimes for 8
## 6.1 add failing test
5,6,7은 추가적인 작업 없이도 정상 동작한다. 8은 예외다. 8에 대한 failing test를 추가한다.

```
@Test
public void
canFactorIntoPrimes() {
    assertPrimeFactors(1, list());
    assertPrimeFactors(2, list(2));
    assertPrimeFactors(3, list(3));
    assertPrimeFactors(4, list(2, 2));
    assertPrimeFactors(5, list(5));
    assertPrimeFactors(6, list(2, 3));
    assertPrimeFactors(7, list(7));
    assertPrimeFactors(8, list(2, 2, 2));
}
```
## 6.2 make it pass
if를 while로 바꿈음로써 최소한의 타이핑으로 성공하도록 한다. play little golf game

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    if (n > 1) {
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }        
    }
    if (n > 1)
        factors.add(n);
    return factors;
}
```
# 7. canFactorIntoPrimes for 9
## 7.1 add failing test

```
assertPrimeFactors(9, list(3, 3));
```
## 7.2 make it pass

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    if (n > 1) {
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }
        while (n % 3 == 0) {
            factors.add(3);
            n /= 3;
        }
    }
    if (n > 1)
        factors.add(n);
    return factors;
}
```

## 7.3 refactor
중복이 발생

- `while(n % 3)` 코드 블록 추가
- 2를 divisor로 추출
- 첫번째 `if (n > 1) {`를 `while (n > 1) {`로 변경

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    int divisor = 2;
    while (n > 1) {
        while (n % divisor == 0) {
            factors.add(divisor);
            n /= divisor;
        }
        divisor++;
    }
    if (n > 1)
        factors.add(n);
    return factors;
}
```

내부 while loop을 for loop으로 변경

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    int divisor = 2;
    while (n > 1) {
        for (;n % divisor == 0;n /= divisor)
            factors.add(divisor);
        divisor++;
    }
    if (n > 1)
        factors.add(n);
    return factors;
}
```

외부 while loop을 for loop으로 변경

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    int divisor = 2;
    for (;n > 1;divisor++)
        for (;n % divisor == 0;n /= divisor)
            factors.add(divisor);
    if (n > 1)
        factors.add(n);
    return factors;
}
```
divisor 선언을 for loop으로 이동

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();
    
    for (int divisor = 2;n > 1;divisor++)
        for (;n % divisor == 0;n /= divisor)
            factors.add(divisor);
    if (n > 1)
        factors.add(n);
    return factors;
}
```
이제 불필요해진 if(n > 1) 코드 블록(외부 for loop의 실행조건이 n > 1이다. n <= 1이면 종료된다)을 제거

```
private List<Integer> of(int n) {
    List<Integer> factors = new ArrayList<Integer>();

    for (int divisor = 2;n > 1;divisor++)
        for (;n % divisor == 0;n /= divisor)
            factors.add(divisor);
    return factors;
}
```

# 8. add more test cases

`assertPrimeFactors(2 * 2 * 3 * 3 * 3 * 7 * 7 * 11 * 11 * 13, list(2, 2, 3, 3, 3, 7, 7, 11, 11, 13));`