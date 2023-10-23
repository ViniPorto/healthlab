create table Metodo(
    MetodoId bigint not null auto_increment,
    MetodoNome varchar(50) not null,
    MetodoDescricao text,

    primary key(MetodoId)
);

insert into Metodo(MetodoNome) values ('RESISTIVIDADE - IMPEDÂNCIA - MICROSCOPIA');
insert into Metodo(MetodoNome, MetodoDescricao) values ('ELETRODO SELETIVO', 'Um eletrodo seletivo é um dispositivo usado na análise e medição de íons específicos em soluções aquosas. Os eletrodos seletivos são amplamente utilizados em química analítica, bioquímica, e outros campos para medir concentrações de íons em soluções, como íons de hidrogênio (pH), íons de metais pesados, íons de sódio, potássio, entre outros.

A principal característica de um eletrodo seletivo é a sua capacidade de responder seletivamente a um tipo específico de íon em uma solução. Isso é alcançado por meio de membranas ou materiais que são sensíveis ao íon-alvo e que geram uma diferença de potencial elétrico em resposta à concentração desse íon.

Alguns tipos comuns de eletrodos seletivos incluem:

Eletrodo de pH: Este tipo de eletrodo é usado para medir a concentração de íons de hidrogênio (H+), que é um indicador da acidez ou alcalinidade de uma solução (pH).

Eletrodo de íons seletivos (ISE): Esses eletrodos são projetados para medir concentrações específicas de íons, como sódio (Na+), potássio (K+), cloro (Cl-), entre outros. Cada tipo de ISE é sensível a um íon específico e, portanto, é seletivo para ele.

Eletrodo de metal pesado: Usado para medir concentrações de metais pesados, como chumbo, mercúrio e cádmio.

Eletrodo de gás: Utilizado para medir a concentração de gases dissolvidos, como o oxigênio (O2) ou dióxido de carbono (CO2), em soluções aquosas.

Os eletrodos seletivos funcionam com base no princípio de que a diferença de potencial elétrico gerada é proporcional à concentração do íon-alvo na solução. Essa diferença de potencial elétrico é medida e usada para calcular a concentração do íon de interesse.

Os eletrodos seletivos são ferramentas valiosas em laboratórios de análises químicas e em aplicações que envolvem o monitoramento de parâmetros químicos em processos industriais, meio ambiente e cuidados de saúde. Eles são especialmente úteis devido à sua seletividade para íons específicos, o que os torna ideais para medições precisas em uma variedade de aplicações.');
insert into Metodo(MetodoNome, MetodoDescricao) values ('COLORIMÉTRICO', 'O método colorimétrico é uma técnica de análise química que se baseia na medição da absorção ou intensidade da cor de uma solução química. É amplamente utilizado para determinar a concentração de uma substância em uma solução, identificar a presença de substâncias específicas e medir as propriedades químicas de uma amostra com base na cor que ela apresenta quando reage com um reagente específico.

O método colorimétrico envolve os seguintes passos:

Reagente específico: Um reagente químico é adicionado à amostra, resultando em uma reação que produz uma substância colorida. A cor gerada está relacionada à concentração ou à presença da substância-alvo na amostra.

Medição da cor: Um instrumento chamado colorímetro ou espectrofotômetro é usado para medir a absorção da luz pela solução colorida. A intensidade da cor é medida em uma determinada faixa de comprimento de onda, geralmente na região visível do espectro eletromagnético.

Análise da absorção: A absorção da luz pela solução é proporcional à concentração da substância-alvo. Quanto maior a concentração da substância, maior será a absorção de luz e, portanto, mais intensa será a cor observada.

O método colorimétrico é amplamente utilizado em várias áreas, incluindo:

Análises clínicas: Na medicina, o método colorimétrico é usado para medir a concentração de biomarcadores em amostras de sangue, urina e outros fluidos corporais, permitindo o diagnóstico e o monitoramento de várias condições de saúde.

Controle de qualidade industrial: É usado para monitorar a concentração de substâncias em produtos químicos, alimentos, bebidas e produtos farmacêuticos.

Análises ambientais: É usado para detectar poluentes em água, solo e ar.

Pesquisas científicas: É amplamente aplicado em pesquisas científicas para quantificar a concentração de substâncias em experimentos e análises.

Indústria de alimentos: Para medir a concentração de componentes, como açúcares, proteínas, gorduras e vitaminas em alimentos.

O método colorimétrico é uma técnica versátil que oferece resultados rápidos e relativamente simples de interpretar. A precisão da análise depende da qualidade do equipamento, do reagente e das condições de medição. É uma ferramenta valiosa em laboratórios de análises químicas e em muitas aplicações em que a medição de concentrações ou identificação de substâncias com base em sua cor é fundamental.');
