import random
from collections import deque

class Dice:
    def __init__ (self,faces:int=6):
        self.faces=faces

    def roll(self)->int:
        return random.randint(1,self.faces)
    
class Player:
    def __init__(self,name:str):
        self.name=name
        self.position=0

    def move(self,steps:int):
        self.position+=steps
    
class Board:
    def __init__(self,size:int,snakes:dict[int,int],ladders:dict[int,int]):
        self.size=size
        self.snakes=snakes
        self.ladders=ladders

    def get_final_position(self,position:int)->int:
        if position in self.snakes:
            return self.snakes[position]
        if position in self.ladders:
            return self.ladders[position]
        return position

class Game:
    def __init__(self,players:list[Player],board:Board,dice:Dice):
        self.players=deque(players)
        self.board=board
        self.dice=dice
        self.winner=None

    def play_turn(self,player:Player):
        dice_value=self.dice.roll()
        print(f"{player.name} rolled {dice_value}")

        if player.position+dice_value>self.board.size:
            return
        
        player.move(dice_value)
        print(f"{player.name} moved to {player.position}")

        final_position = self.board.get_final_position(player.position)
        if final_position != player.position:
            print(f"{player.name} moved to {final_position} due to snake/ladder")

        player.position = final_position

        if player.position == self.board.size:
            self.winner = player
    
    def play(self):
        while not self.winner:
            player=self.players.popleft()
            self.play_turn(player)

            if self.winner:
                print(f"\nWinner is {player.name}")
                break

            self.players.append(player)

if __name__ == "__main__":
    snakes = {
        99: 54,
        70: 55,
        52: 42,
        25: 2
    }

    ladders = {
        6: 25,
        11: 40,
        60: 85,
        46: 90
    }

    board = Board(100, snakes, ladders)
    dice = Dice()

    players = [
        Player("Harshil"),
        Player("Aman"),
        # Player("Rohit")
    ]

    game = Game(players, board, dice)
    game.play()