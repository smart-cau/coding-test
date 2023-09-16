# TIL

* input 자료의 범위를 반드시 고려해야 한다. 첫 시도 때 C의 범위를 고려 안해 메모리 초과 남
* 백준 제출 코드는 반드시 Integer와 같은 wrapper class 간의 비교는 ==이 아닌 equals()로 비교해야 함. Integer의 경우 로컬에서는 == 으로해도 값 비교를 하지만 백준에서는 주소값 비교를 함
* `stream().asList()`의 인자로 primitive type array를 전달할 시 list로 변환 안됨
    * 처음부터 wrapper class type array로 만들던지 primitive type의 경우 `.boxed().collect(Collectors.toList())`로 list 변환을 해야 함
* ++index와 index++의 차이 명확하게 알아두자.
* `break;`문과 `continue;`문 햇갈리지 말자