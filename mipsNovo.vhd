library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity mips is
    Port (
        Clk           : in  STD_LOGIC;
        Inicializar   : in  STD_LOGIC;
        DebugEndereco : in  STD_LOGIC_VECTOR (31 downto 0);
        DebugPalavra  : out STD_LOGIC_VECTOR (31 downto 0)
    );
end mips;

architecture Behavioral of mips is
    
    -- 1. Banco de Registradores
    component bancoRegistradoresNovo is
        port(
            DadoEscrita : in  std_logic_vector(31 downto 0);
            Clk         : in  std_logic;
            EscreveReg  : in  std_logic;
            EndEscrita  : in  std_logic_vector(4 downto 0);
            EndLeitura1 : in  std_logic_vector(4 downto 0);
            EndLeitura2 : in  std_logic_vector(4 downto 0);
            DadoL1      : out std_logic_vector(31 downto 0);
            DadoL2      : out std_logic_vector(31 downto 0)
        );
    end component;

    -- 2. Deslocador de 2 Bits
    component deslocadorDe2Bits is
        port(
            E : in  std_logic_vector(31 downto 0);
            S : out std_logic_vector(31 downto 0)
        );
    end component;

    -- 3. Extensor de Sinal
    component extensorDeSinalNovo is
        port(
            E : in  std_logic_vector(15 downto 0);
            S : out std_logic_vector(31 downto 0)
        );
    end component;

    -- 4. Memória de Dados
    component memDados is
        port (
            DadoLido      : out std_logic_vector(31 downto 0);
            DadoEscrita   : in  std_logic_vector(31 downto 0);
            Endereco      : in  std_logic_vector(31 downto 0);
            EscreverMem   : in  std_logic;
            Clock         : in  std_logic;
            LerMem        : in  std_logic;
            DebugEndereco : in  std_logic_vector(31 downto 0);
            DebugPalavra  : out std_logic_vector(31 downto 0)
        );
    end component;

    -- 5. Memória de Instruções
    component memInstrucoes is
        port (
            Endereco : in  std_logic_vector(31 downto 0);
            Palavra  : out std_logic_vector(31 downto 0)
        );
    end component;

    -- 6. Multiplexador de 2 Entradas e 32 Bits
    component mux2Entradas32Bits is
        port(
            Selmux2Entradas32Bits : in  std_logic;
            B                     : in  std_logic_vector(31 downto 0);
            A                     : in  std_logic_vector(31 downto 0);
            S                     : out std_logic_vector(31 downto 0)
        );
    end component;

    -- 7. Multiplexador de 2 Entradas e 5 Bits
    component mux2Entradas5Bits is
        port(
            SelMux2Entradas : in  std_logic;
            B               : in  std_logic_vector(4 downto 0);
            A               : in  std_logic_vector(4 downto 0);
            S               : out std_logic_vector(4 downto 0)
        );
    end component;

    -- 8. Registrador do Programa
    component registradorPcNovo is
        port(
            Clock       : in  std_logic;
            Inicializar : in  std_logic;
            D           : in  std_logic_vector(31 downto 0);
            Q           : out std_logic_vector(31 downto 0)
        );
    end component;

    -- 9. Unidade Lógica e Aritmética de 32 Bits
    component ula32bits is
        port(
            A        : in  std_logic_vector(31 downto 0);
            B        : in  std_logic_vector(31 downto 0);
            Op       : in  std_logic_vector(1 downto 0);
            Ainverte : in  std_logic;
            Binverte : in  std_logic;
            Zero     : out std_logic;
            Result   : out std_logic_vector(31 downto 0)
        );
    end component;

    -- 10. Unidade de Controle da ULA
    component unidadeDeControleDaUlaNovo is
        port(
            AluOp    : in  std_logic_vector(1 downto 0);
            Funct    : in  std_logic_vector(5 downto 0);
            AInverte : out std_logic;
            BInverte : out std_logic;
            Operacao : out std_logic_vector(1 downto 0)
        );
    end component;

    -- 11. Unidade de Controle Principal
    component unidadeDeControleNovo is
        port(
            OpCode   : in  std_logic_vector(5 downto 0);
            AluOp    : out std_logic_vector(1 downto 0);
            RegWrite : out std_logic;
            RegDst   : out std_logic;
            AluScr   : out std_logic;
            Branch   : out std_logic;
            MemWrite : out std_logic;
            MemToReg : out std_logic;
            MemRead  : out std_logic;
            Jump     : out std_logic
        );
    end component;
	 
-- 1
		signal EnderecoPC : std_logic_vector(31 downto 0);

-- 2
		signal Instrucao : std_logic_vector(31 downto 0);

-- 3
		signal RegistradorDestino : std_logic_vector(4 downto 0);

-- 4
		signal SaidaMuxRegDst : std_logic_vector(4 downto 0);

-- 5
		signal DadoEscritaRegistrador : std_logic_vector(31 downto 0);

-- 6
		signal HabilitaEscritaRegistrador : std_logic;

-- 7
		signal DadoLidoRegistrador1 : std_logic_vector(31 downto 0);

-- 8
		signal DadoLidoRegistrador2 : std_logic_vector(31 downto 0);

-- 9
		signal ImediatoEstendido : std_logic_vector(31 downto 0);

-- 10
		signal EntradaBULA : std_logic_vector(31 downto 0);

-- 11
		signal SaidaMuxALUSrc : std_logic_vector(31 downto 0);

-- 12
		signal ControleULA : std_logic_vector(1 downto 0);

-- 13
		signal OperacaoULA : std_logic_vector(1 downto 0);

-- 14
		signal InverterA : std_logic;

-- 15
		signal InverterB : std_logic;

-- 16
		signal Zero : std_logic;

-- 17
		signal ResultadoULA : std_logic_vector(31 downto 0);

-- 18
		signal DadoEscritaMemoria : std_logic_vector(31 downto 0);

-- 19
		signal EnderecoMemoria : std_logic_vector(31 downto 0);
	
-- 20
		signal DadoLidoMemoria : std_logic_vector(31 downto 0);

-- 21
		signal SaidaMuxMemToReg : std_logic_vector(31 downto 0);

-- 22
		signal PCMais4 : std_logic_vector(31 downto 0);

-- 23
		signal EnderecoSalto : std_logic_vector(31 downto 0);

-- 24
		signal ImediatoDeslocado : std_logic_vector(31 downto 0);

-- 25
		signal EnderecoDesvio : std_logic_vector(31 downto 0);

-- 26
		signal BranchEZero : std_logic;

-- 27
		signal SaidaAND : std_logic;

-- 28
		signal SaidaMuxBranch : std_logic_vector(31 downto 0);

-- 29
		signal ProximoPC : std_logic_vector(31 downto 0);

-- 30
		signal SaidaMuxJump : std_logic_vector(31 downto 0);

begin

	BANCO : bancoRegistradoresNovo
		port map(
			DadoEscrita
			Clk
			EscreveReg
			EndEscrita
			EndLeitura1
			EndLeitura2
			
			DadoL1
			DadoL2
		);
        
    DESLOCADOR : deslocadorDe2Bits
        port map(
            E => ,
            S => 
        );

    EXTENSOR : extensorDeSinalNovo
        port map(
            E => ,
            S => 
        );

    MEM_DADOS : memDados
        port map(
            DadoLido      => ,
            DadoEscrita   => ,
            Endereco      => ,
            EscreverMem   => ,
            Clock         => ,
            LerMem        => ,
            DebugEndereco => ,
            DebugPalavra  => 
        );

    MEM_INSTRUCOES : memInstrucoes
        port map(
            Endereco => ,
            Palavra  => 
        );

    MUX_32BITS_1 : mux2Entradas32Bits
        port map(
            Selmux2Entradas32Bits => ,
            B                     => ,
            A                     => ,
            S                     => 
        );
    
    MUX_5BITS : mux2Entradas5Bits
        port map(
            SelMux2Entradas => ,
            B               => ,
            A               => ,
            S               => 
        );

    PC : registradorPcNovo
        port map(
            Clock       => ,
            Inicializar => ,
            D           => ,
            Q           => 
        );

    ULA : ula32bits
        port map(
            A        => ,
            B        => ,
            Op       => ,
            Ainverte => ,
            Binverte => ,
            Zero     => ,
            Result   => 
        );

    CONTROLE_ULA : unidadeDeControleDaUlaNovo
        port map(
            AluOp    => ,
            Funct    => ,
            AInverte => ,
            BInverte => ,
            Operacao => 
        );

    CONTROLE_PRINCIPAL : unidadeDeControleNovo
        port map(
            OpCode   => ,
            AluOp    => ,
            RegWrite => ,
            RegDst   => ,
            AluScr   => ,
            Branch   => ,
            MemWrite => ,
            MemToReg => ,
            MemRead  => ,
            Jump     => 
        );
		
    process(Clk, Inicializar)
    begin
        if Inicializar = '1' then
            DebugPalavra <= (others => '0');
            
        elsif rising_edge(Clk) then
            DebugPalavra <= (others => '0');
        end if;
    end process;

end Behavioral;