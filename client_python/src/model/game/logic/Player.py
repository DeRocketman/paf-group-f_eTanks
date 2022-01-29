from model.data.User import User


class Player(User):
    def __init__(self):
        super().__init__()
        self.isRdy = False
