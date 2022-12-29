package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    void shouldFetchEmptyBoardList() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(List.of());
        //When
        List<TrelloBoardDto> fetchedTrelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        assertThat(fetchedTrelloBoardDtos).isNotNull();
        assertEquals(0, fetchedTrelloBoardDtos.size());
    }

    @Test
    void testCreateTrelloBoard () {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test", "test", "test");
        //when
        trelloService.createTrelloCard(trelloCardDto);
        //then
        verify(trelloClient, times(1)).createNewCard(trelloCardDto);
    }
}