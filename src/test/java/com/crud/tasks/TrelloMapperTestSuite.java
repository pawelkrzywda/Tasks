package com.crud.tasks;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloBoard trelloBoard;

    @Mock
    private TrelloBoardDto trelloBoardDto;

    @Mock
    private TrelloListDto trelloListDto;

    @Mock
    private TrelloList trelloList;

    @Mock
    private TrelloCard trelloCard;

    @Mock
    private TrelloCardDto trelloCardDto;

    @Test
    void testMapToBoards(){
        //Given
        List<TrelloListDto> testList = new ArrayList<>();
        testList.add(trelloListDto);
        trelloBoardDto = new TrelloBoardDto("test", "test", testList);
        List<TrelloBoardDto> trelloBoardDtoTestList = new ArrayList<>();
        trelloBoardDtoTestList.add(trelloBoardDto);
        //When
        List<TrelloBoard> testBoard = trelloMapper.mapToBoards(trelloBoardDtoTestList);
        //Then
        assertEquals(1, testBoard.size());
        assertEquals("test", testBoard.get(0).getName());
    }

    @Test
    void testMapToBoardsDto(){
        //Given
        List<TrelloList> testList = new ArrayList<>();
        testList.add(trelloList);
        trelloBoard = new TrelloBoard("testDto", "testDto", testList);
        List<TrelloBoard> trelloBoards =  new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When
        List<TrelloBoardDto> trelloBoardDtoTestList = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(1, trelloBoardDtoTestList.size());
        assertEquals("testDto", trelloBoardDtoTestList.get(0).getName());
    }

    @Test
    void testMapToList(){
        //Given
        List<TrelloListDto> testListDto = new ArrayList<>();
        testListDto.add(trelloListDto);
        //When
        List<TrelloList> testList = trelloMapper.mapToList(testListDto);
        //Then
        assertEquals(1, testList.size());
    }

    @Test
    void testMapToListDto(){
        //Given
        List<TrelloList> testList = new ArrayList<>();
        testList.add(trelloList);
        //When
        List<TrelloListDto> testListDto = trelloMapper.mapToListDto(testList);
        //Then
        assertEquals(1, testListDto.size());
    }

    @Test
    void testMapToCard(){
        //Given
        trelloCardDto = new TrelloCardDto("testName", "testDescription", "testPosition", "testId");
        //When
        TrelloCard testCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("testName", testCard.getName());
        assertEquals("testDescription", testCard.getDescription());
        assertEquals("testPosition", testCard.getPos());
        assertEquals("testId", testCard.getListId());
    }

    @Test
    void testMapToCardDto(){
        //Given
        trelloCard = new TrelloCard("testName", "testDescription", "testPosition", "testId");
        //When
        TrelloCardDto testCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("testName", testCardDto.getName());
        assertEquals("testDescription", testCardDto.getDescription());
        assertEquals("testPosition", testCardDto.getPos());
        assertEquals("testId", testCardDto.getListId());
    }
}
