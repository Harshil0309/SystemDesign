
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
        self.board = [None*size for _ in range(size)]

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
        return

    def is_full(self):
        for row in self.board:
            for cell in row:
                if cell is None:
                    return False
        return True
        