
class PlayingPiece:
    def __init__(self,piece_type):
        self.piece_type=piece_type

    def __str__(self):
        return self.piece_type

class PieceX(PlayingPiece):
    def __init__(self):
        super().__init__('X')

class PieceO(PlayingPiece):
    def __init__(self):
        super().__init__('O')

class Player:
    def __init__(self,name,piece:PlayingPiece):
        self.name=name
        self.piece=piece

class Board:
    def __init__(self,size):
        self.size=size
        self.board = [[None]*size for _ in range(size)]

    def print_board(self):
        for row in self.board:
            print(" | ".join([str(cell) if cell else " " for cell in row]))
            print("-" * (self.size * 4))

    def place_piece(self,row,col,piece):
        if 0<=row<self.size and 0<=col<self.size:
            if self.board[row][col] is None:
                self.board[row][col]=piece
                return True
        return False

    def check_win(self,row,col,piece):
        # Check if N continuous same pieces exist
        # graph island logic
        target=piece.piece_type
        N=self.size
        directions=[(0,1),(1,0),(1,1),(1,-1)]

        for dr,dc in directions:
            count=1
            r,c=row+dr,col+dc
            while 0<=r<N and 0<=c<N and self.board[r][c] and self.board[r][c].piece_type==target:
                count+=1
                r+=dr
                c+=dc

            r,c=row-dr,col-dc
            while 0<=r<N and 0<=c<N and self.board[r][c] and self.board[r][c].piece_type==target:
                count+=1
                r-=dr
                c-=dc 
            
            if count>=N:
                return True
        return False


        return

    def is_full(self):
        for row in self.board:
            for cell in row:
                if cell is None:
                    return False
        return True
        
class Game:
    def __init__(self,size):
        self.board=Board(size)
        self.players=[]
        self.current_turn=0

    def add_player(self,player):
        self.players.append(player)

    def switch_turn(self):
        self.current_turn =1-self.current_turn
    
    def start(self):
        print("\n--- TIC TAC TOE ---")
        print(f"Board: {self.board.size} x {self.board.size} | Win Condition: {self.board.size} in a row\n")

        self.board.print_board()

        while True:
            player=self.players[self.current_turn]
            print(f"{player.name}'s turn ({player.piece})")

            try:
                row = int(input("Enter row: "))
                col = int(input("Enter col: "))
            except:
                print("Invalid input. Try again.")
                continue

            if not self.board.place_piece(row,col,player.piece):
                print("Invalid input. Try again.")
                continue

            self.board.print_board()

            if self.board.check_win(row,col,player.piece):
                print(f"\n🎉 {player.name} WINS! 🎉")
                break

            if self.board.is_full():
                print("\nIt's a DRAW!")
                break

            self.switch_turn()

def main():
    N=int(input("Enter board size N: "))
    game=Game(N)
    print("line122")

    player1=Player("Player 1",PieceX())
    player2=Player("Player 2",PieceO())
    game.add_player(player1)
    game.add_player(player2)

    game.start()


if __name__== "__main__":
    main()

# https://chatgpt.com/share/6929d7e5-cee4-8000-a513-4585d751e6e5
# https://medium.com/@riyag283/tic-tac-toe-low-level-design-b0bd44965fd9