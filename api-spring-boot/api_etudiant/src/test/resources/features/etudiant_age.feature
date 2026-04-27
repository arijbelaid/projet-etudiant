Feature: Calcul de l'âge d'un étudiant

  Scenario: Un étudiant né le 15 janvier 2003 doit avoir l'âge correct
    Given un étudiant nommé "Arij Belaid" né le "2003-01-15"
    Then son âge doit être au moins 23 ans

  Scenario: Un étudiant né le 22 mai 2001 doit avoir l'âge correct
    Given un étudiant nommé "Bader Chetoui" né le "2001-05-22"
    Then son âge doit être au moins 24 ans