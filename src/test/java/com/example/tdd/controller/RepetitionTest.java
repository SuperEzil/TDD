package com.example.tdd.controller;

import com.example.tdd.data.UserInfo;
import com.example.tdd.tags.RepeatedTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * 반복 & 인자 값 테스트 정의
 */
class RepetitionTest {

    @RepeatedTag
    @DisplayName("RepeatedTest")
    @RepeatedTest(value = 5, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatedGetUser(RepetitionInfo info) {
        assertNotNull(info);
        System.out.println("repeatedGetUser: "+info.getCurrentRepetition());
    }


    @RepeatedTag
    @ParameterizedTest(name = "{index} {displayName}={arguments}")
    @DisplayName("ValueSource")
    @ValueSource(strings = {"user1", "user4", "user2", "user3"})
    void ValueSource(String name){
        assertNotNull(name);
        System.out.println("ValueSource: "+name);
    }

    @RepeatedTag
    @ParameterizedTest(name = "{index} {displayName}={arguments}")
    @DisplayName("ValueSource - CustomArgumentConverter")
    @ValueSource(ints = {1,3,4,3})
    void valueSourceSimpleArgumentConverter(@ConvertWith(UserInfoArgumentConverter.class) UserInfo info){
        assertNotNull(info);
        System.out.println("ValueSource: "+ info.getName());
    }

    /**
     * SimpleArgumentConverter 제약 조건
     * <li> 1:1 Argument 만 변환 가능
     */
    static class UserInfoArgumentConverter extends SimpleArgumentConverter{

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(UserInfo.class, targetType, "UserInfo 타입 클래스만 사용 가능합니다.");
            String value = Integer.toString((Integer) source);
            return new UserInfo(value, value);
        }
    }


    @RepeatedTag
    @ParameterizedTest(name = "{index} {displayName}={arguments}")
    @DisplayName("CsvSource")
    @CsvSource({"user1, password1", "user3, password3", "user2, password2"})
    void CsvSource(String name, String password){
        assertNotNull(name);
        System.out.println(String.format("name=%s, password=%s", name, password));
    }


    @RepeatedTag
    @ParameterizedTest(name = "{index} {displayName}={arguments}")
    @DisplayName("CsvSource - ArgumentsAccessor")
    @CsvSource({"user1, password1", "user3, password3", "user2, password2"})
    void CsvSourceArgumentsAccessor(ArgumentsAccessor argumentsAccessor){
        assertNotNull(argumentsAccessor);
        UserInfo userInfo = new UserInfo((String) argumentsAccessor.get(0), (String) argumentsAccessor.get(1));
        System.out.println(String.format("name=%s, password=%s", userInfo.getName(), userInfo.getPassword()));
    }


    @RepeatedTag
    @ParameterizedTest(name = "{index} {displayName}={arguments}")
    @DisplayName("CsvSource - CustomArgumentsAccessor")
    @CsvSource({"user1, password1", "user3, password3", "user2, password2"})
    void CsvSourceCustomArgumentsAccessor(@AggregateWith(UserInfoArgumentsAccessor.class) UserInfo userInfo){
        assertNotNull(userInfo);
        System.out.println(String.format("name=%s, password=%s",userInfo.getName(), userInfo.getPassword()));
    }

    /**
     * Custom ArgumentsAggregator 제약 조건
     * <li> Inner & Static Class
     * <li> Public Class
     */
    static class UserInfoArgumentsAccessor implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new UserInfo(accessor.getString(0), accessor.getString(1));
        }
    }


    @RepeatedTag
    @ParameterizedTest(name = "{index} {displayName}={arguments}")
    @DisplayName("MethodSource - Value")
    @MethodSource("userInfos")
    void MethodSource(String name, Integer number){
        assertNotNull(name);
        System.out.println(String.format("name=%s, number=%d",name, number));
    }

    private static Stream<Arguments> userInfos(){
        return Stream.of(
            Arguments.arguments("user1", 1111)
            ,Arguments.arguments("user4", 4444)
                ,Arguments.arguments("user2", 2222)
                ,Arguments.arguments("user3", 3333)

        );
    }




}