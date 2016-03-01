#Requisitos do banco de exemplo.

# Requisitos #

**RFN01** -  O banco deve permitir cadastrar contas dos tipos Corrente, Poupanca, Especial e Imposto. Contas são identificadas unicamente pelo seu número.

**RFN02** - Apenas contas válidas (saldo positivo e número não vazio) são aceitas pelo sistema.

**RFN03** - As contas do banco devem continuar válidas após as operações.

**RFN04** - As operações permitidas para todos os tipos de contas são: creditar, debitar e saldo.

**RFN05** - A operação render juros funciona apenas para conta poupança.

**RFN06** - A operação render bônus funciona apenas para conta especial.

**RFN07** - A operação debitar em uma conta imposto abate (além do valor solicitado) o valor do imposto CPMF (0.38%) proporcional ao valor debitado.

**RFN08** - É possível transferir valores entre quaisquer tipos de contas.