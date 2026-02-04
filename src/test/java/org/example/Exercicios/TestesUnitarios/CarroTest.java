package org.example.Exercicios.TestesUnitarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class CarroTest {
    
    Carro carro;

    @BeforeEach
    void setUp() {
        // Inicializa um carro com velocidade máxima de 200 antes de cada teste
        carro = new Carro(200);
    }

    @Test
    @DisplayName("Deve acelerar corretamente dentro do limite")
    void deveAcelerarCorretamente() {
        // Act
        carro.acelerar(50);
        
        // Assert
        assertEquals(50, carro.getVelocidadeAtual());
    }

    @Test
    @DisplayName("Não deve ultrapassar a velocidade máxima")
    void naoDeveUltrapassarVelocidadeMaxima() {
        // Act: Tenta acelerar mais que o limite (200)
        carro.acelerar(250);
        
        // Assert: Deve travar em 200
        assertEquals(200, carro.getVelocidadeAtual());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar acelerar valor negativo")
    void deveLancarErroAoAcelerarNegativo() {
        // Assert: Verifica se o código explode com o erro certo
        assertThrows(IllegalArgumentException.class, () -> carro.acelerar(-10));
    }

    @Test
    @DisplayName("Não deve ter velocidade negativa ao frear")
    void naoDeveTerVelocidadeNegativaAoFrear() {
        // Arrange: Acelera um pouco primeiro
        carro.acelerar(50);

        // Act: Freia muito (100)
        carro.frear(100);

        // Assert: Deve travar em 0, não pode ser -50
        assertEquals(0, carro.getVelocidadeAtual());
    }
}
