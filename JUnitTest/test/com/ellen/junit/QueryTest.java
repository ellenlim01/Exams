package com.ellen.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryTest {

    @BeforeAll
    public static void beforeAll() {
        Query.createTable();
    }

    @AfterAll
    public static void afterAll() {
        Query.dropTable();
    }

    @BeforeEach
    public void beforeEach() {
        Query.boardDelete(0);
        BoardVO vo1 = new BoardVO();
        vo1.setBtitle("Ellen");
        vo1.setBcontent("Lim");
        Query.boardInsert(vo1);

        BoardVO vo2 = new BoardVO();
        vo2.setBtitle("LJY");
        vo2.setBcontent("Jiyeong");
        Query.boardInsert(vo2);
    }

    @Test
    public void testA() {
        BoardVO vo1 = Query.getBoardDetail(1);
        assertEquals(vo1.getBtitle(), "Ellen");
        assertEquals(vo1.getBcontent(), "Lim");

        BoardVO vo2 = Query.getBoardDetail(2);
        assertEquals(vo1.getBtitle(), "LJY");
        assertEquals(vo1.getBcontent(), "Jiyeong");
    }

    @Test
    public void testB() {

    }

    @Test
    public void testC() {
        BoardVO C = new BoardVO();
        BoardVO D = new BoardVO();
        C.setBid(1);
        C.setBtitle("A");
        C.setBcontent("a");
        D.setBid(2);
        D.setBtitle("B");
        D.setBcontent("b");
        assertEquals(Query.getBoardDetail(1), C);
        assertEquals(Query.getBoardDetail(2), D);
    }

}
