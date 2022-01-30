# Importieren u. initialisieren der Pygame-Bibliothek
import pygame
from pygame.locals import *

pygame.init()

# Variablen/KONSTANTEN setzen
W, H = 800, 600
FPS = 60
SCHWARZ = (0, 0, 0)
WEISS = (255, 255, 255)
GRAU = (155, 155, 155)
spielaktiv = True

spielerfigur = pygame.image.load("../../resources/images/tank/Tank_01.png")

# Definieren und Öffnen eines neuen Fensters
fenster = pygame.display.set_mode((W, H))
pygame.display.set_caption("Grafik skalieren")
clock = pygame.time.Clock()

# Schleife Hauptprogramm
while spielaktiv:
    # Überprüfen, ob Nutzer eine Aktion durchgeführt hat
    for event in pygame.event.get():
        # Beenden bei [ESC] oder [X]
        if event.type == QUIT or (event.type == KEYDOWN and event.key == K_ESCAPE):
            spielaktiv = False

    # Spiellogik

    # Spielfeld löschen
    fenster.fill(GRAU)

    # Spielfeld/figuren zeichnen
    bienen_klein = pygame.transform.scale(spielerfigur, (150, 150))
    bienen_mittel = pygame.transform.scale(spielerfigur, (250, 250))
    bienen_gross = pygame.transform.scale(spielerfigur, (350, 350))
    fenster.blit(bienen_klein, (500, 60))
    fenster.blit(bienen_mittel, (340, 190))
    fenster.blit(bienen_gross, (80, 60))

    # Fenster aktualisieren
    pygame.display.flip()
    clock.tick(FPS)
