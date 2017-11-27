import com.dubemarcantoine.comp352.smartar.Car
import com.dubemarcantoine.comp352.smartar.datastructure.implementation.SmartAR
import spock.lang.Specification
import spock.lang.Unroll

class SmartARTest extends Specification {

    @Unroll
    def "Smart AR usage"() {
        SmartAR<String, Car> smartAR = new SmartAR<>()
        values.each { val ->
            smartAR.add(val, new Car(val))
        }

        when:
        for (int i = 0; i < repeatCountValue; i++) {
            smartAR.add(usedValue, new Car(usedValue))
        }

        boolean isRemoved = smartAR.remove(usedValue)
        String prevKey = smartAR.prevKey(usedValue)
        String nextKey = smartAR.nextKey(usedValue)
        List<Car> values = smartAR.getValues(usedValue)
        List<Car> previousCars = smartAR.previousCars(usedValue)
        List<String> generatedKeys = smartAR.generate(generateSize)

        then:
        isRemoved == shouldRemoveValue

        and:
        prevKey == keyPrevKey

        and:
        nextKey == keyNextKey

        and:
        getValueSize == values?.size()

        and:
        previousValueSize == previousCars?.size()

        and:
        generateSize == generatedKeys?.size()

        where:
        values                            | usedValue  | repeatCountValue | shouldRemoveValue | keyPrevKey | keyNextKey | getValueSize | previousValueSize | generateSize
        ['0000000', '0000001', '0000010'] | '00000011' | 5                | true              | '0000010'  | null       | 5            | 5                 | 1000
        []                                | '0000000'  | 500              | true              | null       | null       | 500          | 500               | 1000
        ['000000']                        | '0000001'  | 1                | true              | '000000'   | null       | 1            | 1                 | 3
        ['000000']                        | '0000001'  | 0                | false             | '000000'   | null       | 0            | 0                 | 3
        ['000002']                        | '0000001'  | 1                | true              | null       | '000002'   | 1            | 1                 | 3
        ['000002']                        | '0000001'  | 0                | false             | null       | '000002'   | 0            | 0                 | 3
        []                                | '00000011' | 0                | false             | null       | null       | 0            | 0                 | 0
        []                                | '00000011' | 5000             | true              | null       | null       | 5000         | 5000              | 0
        []                                | '00000011' | 50000            | true              | null       | null       | 50000        | 50000             | 0
        []                                | '00000011' | 500000           | true              | null       | null       | 500000       | 500000            | 0
    }
}
