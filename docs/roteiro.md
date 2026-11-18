# Roteiro do apresentador — 15 minutos

Objetivo: entender o caminho do código ao APK, reconhecer uma falha de teste e reproduzir a execução depois no próprio fork.

## Preparação

- Conecte este repositório ao Codemagic e faça uma execução completa antes da aula.
- Deixe abertos: README, `codemagic.yaml`, `Counter.java`, `CounterTest.java`, uma execução concluída e o APK.
- Prepare, em um fork de demonstração, uma execução com `value += 2;` e outra com a correção. Guarde prints dos logs e do relatório.
- Faça um ensaio cronometrado. O primeiro build pode baixar dependências e ultrapassar a janela da demonstração.
- Deixe a [página de encerramento com QR code](encerramento.md) pronta para o minuto final. Se usar slides, reutilize [o SVG](assets/repositorio-qr.svg), preservando a margem branca ao redor do código.
- Evite gastar a aula com cadastro e autorização de contas: demonstre com a conta já preparada e deixe a reprodução para casa.

## 00:00–07:00 — Teoria com um resultado concreto

| Tempo | Ação e fala de apoio |
|---|---|
| 00:00–01:00 | Mostre o contador instalado ou o APK pronto. “A ideia é sair de um código no GitHub e chegar a um aplicativo que podemos instalar.” |
| 01:00–02:00 | Apresente a dor: “A cada mudança, precisamos conferir se o app funciona e gerar uma nova versão. Podemos deixar essa sequência configurada.” |
| 02:00–03:00 | Explique CI: integrar mudanças com verificações frequentes. Explique CD: automatizar preparação e entrega de versões. Delimite o resultado da aula: testes e APK de debug. |
| 03:00–04:00 | Situe o Codemagic como o serviço que obtém o código e executa a receita em uma máquina de build. O Gradle compila o app nessa máquina. |
| 04:00–05:00 | Mostre a sequência: obter código → preparar ambiente → testar → compilar → disponibilizar APK. Se os testes falham, não seguimos ao build. |
| 05:00–06:00 | Defina workflow como receita; gatilho como evento que inicia a execução; artefato como arquivo resultante. Use os nomes presentes no projeto. |
| 06:00–07:00 | Abra o YAML. Aponte `workflows`, `triggering`, `scripts` e `artifacts`, sem ler cada linha. Relacione à sequência anterior. |

## 07:00–08:00 — Quiz

Use [as três perguntas](quiz.md), com 15 segundos para cada uma e 15 segundos para corrigir. Gabarito: **A, B, B**.

Se houver confusão, retome o conceito antes de avançar: “O gatilho inicia a receita; as etapas testam e compilam; o APK é um dos resultados.” O quiz fornece uma checagem rápida, não uma garantia de aprendizagem individual.

## 08:00–14:00 — Demonstração

| Tempo | Ação e fala de apoio |
|---|---|
| 08:00–09:00 | Abra o repo. Mostre `app/`, o teste do contador e o YAML. Explique que o exemplo já está pronto para concentrar a aula na pipeline. |
| 09:00–10:00 | No Codemagic, mostre o repositório conectado e selecione branch e workflow `android-debug`. Aponte a configuração por YAML. |
| 10:00–11:00 | Inicie o build manualmente. Mostre também o gatilho `push` e explique a dependência do webhook. |
| 11:00–12:00 | Abra os logs. Localize preparação e testes. Se estiver em fila, use a execução concluída e explique que ela foi preparada anteriormente. |
| 12:00–13:00 | Mostre os quatro testes e os artefatos de uma execução bem-sucedida. Baixe o APK. Se o build ao vivo estiver rodando, use o anterior sem sugerir que já terminou. |
| 13:00–14:00 | Mostre a falha preparada: incremento de dois quebra `incrementsExactlyOnePerClick`. A etapa de gerar APK não executa. Mostre a execução corrigida e conecte à tarefa. |

## 14:00–15:00 — Repositório, star, fork e tarefa

| Tempo | Encaminhamento |
|---|---|
| 14:00–14:15 | Mostre a [página com QR code e link do repositório](encerramento.md). “Abram pelo link ou pelo QR code.” |
| 14:15–14:25 | Aponte **Star**. “Marquem o material para encontrar depois.” |
| 14:25–14:40 | Mostre **Fork → Create fork**. “Criem uma cópia na conta de vocês; é nela que vão trabalhar.” |
| 14:40–15:00 | Abra [a tarefa de casa](tarefa-de-casa.md). “Personalizem a mensagem, observem um teste falhar, corrijam e gerem o APK. Registrem os dois resultados no fork. O passo a passo está aqui.” |

A tarefa será feita depois. O minuto final serve para a pessoa localizar o material, criar sua cópia e entender o que entregar.

## Critério para encerrar

Ao final, deixe visível o README com **Comece por aqui** e **Tarefa de casa**. Dúvidas de instalação e publicação em loja ficam para depois da janela de 15 minutos.
