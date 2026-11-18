# Do código ao APK: Android com Codemagic

Minicurso de **15 minutos** para entender uma pipeline de CI/CD e acompanhar a geração de um APK Android. O exemplo é um contador: soma um por toque, permite zerar e preserva o valor ao recriar a tela.

**[Comece por aqui](#comece-por-aqui)** · **[Tarefa de casa](docs/tarefa-de-casa.md)** · [Quiz](docs/quiz.md) · [Roteiro do apresentador](docs/roteiro.md) · [QR code](docs/encerramento.md)

## Comece por aqui

Você precisa de uma conta no GitHub, uma conta no [Codemagic](https://codemagic.io/) e um navegador. Para a primeira execução na nuvem, não precisa instalar Android Studio, Java ou SDK no seu computador.

1. Dê uma **Star** neste repositório para marcar o material.
2. Clique em **Fork → Create fork** e crie a cópia na sua conta.
3. Entre no Codemagic, conecte sua conta GitHub e escolha **Add application**.
4. Selecione **o seu fork**, autorize o acesso e escolha o tipo de projeto **Android nativo / Other**, conforme as opções da interface. Use a configuração por **YAML** deste repositório.
5. Inicie um build com **Start new build**, selecione a branch do fork (originalmente `trunk`) e o workflow **`android-debug` — Android - testes e APK**.
6. Acompanhe **Preparar ambiente Android → Executar testes unitários → Gerar APK de debug**.
7. Quando a execução terminar com sucesso, abra os artefatos e baixe **`app-debug.apk`**. O relatório dos testes também fica disponível.
8. Continue na **[tarefa de casa](docs/tarefa-de-casa.md)**.

Os nomes dos botões podem variar conforme a interface. Se o fork não aparecer, confira a permissão do aplicativo GitHub do Codemagic para aquele repositório. Se o YAML não aparecer, confira se está selecionando a branch que contém `codemagic.yaml` na raiz.

O workflow usa `mac_mini_m2`, que também compila Android. A disponibilidade de máquinas e minutos depende da conta; consulte o [plano atual](https://codemagic.io/pricing/) antes de executar. Não é necessário configurar Google Play, keystore de release ou segredos para esta atividade.

## O que acontece na pipeline?

| Conceito | Neste projeto |
|---|---|
| Workflow | `android-debug`: a receita completa de uma execução |
| Gatilho | Disparo manual ou `push` nas branches do fork conectado |
| Preparação | Localiza o Android SDK e permite executar o Gradle Wrapper |
| Testes | Confere as regras do contador com JUnit, sem emulador |
| Build | Compila e empacota o app com `assembleDebug` |
| Artefato | APK instalável para experimentar e relatório de testes |

O [codemagic.yaml](codemagic.yaml) já contém testes antes do build. Se os testes falharem, a etapa que gera o APK não deve executar. O disparo automático depende de o **webhook do Codemagic estar conectado ao seu fork**; adicionar o YAML sozinho não conecta a conta.

CI é integrar mudanças com verificações frequentes. CD envolve automatizar a preparação e a entrega de versões. Aqui praticamos testes e geração de um APK de debug: publicar na Play Store é uma etapa posterior.

## Instalar o APK

Abra o APK baixado em um aparelho **Android 6.0 ou superior** e, se necessário, autorize a instalação pela origem usada para abrir o arquivo. Use seu aparelho de testes. Também é possível instalar em um emulador com `adb install -r app-debug.apk`.

Teste manualmente: tocar duas vezes em **Somar 1** deve mostrar `2`; tocar em **Zerar contador** deve mostrar `0`. Ao girar a tela, o valor deve continuar. Esses comportamentos de interface não são cobertos pelos testes unitários do contador.

Cada máquina de CI pode gerar sua própria chave de debug. Se uma instalação anterior do mesmo pacote tiver uma assinatura diferente, desinstale a versão anterior antes de instalar o novo APK. O APK de debug serve à atividade; uma publicação em loja exige configuração de release e assinatura próprias.

## Executar localmente (opcional)

Requisitos: **JDK 17**, Android SDK com **Platform 35** e **Build Tools 34.0.0**, instaláveis pelo SDK Manager do Android Studio. O projeto fixa AGP 8.7.3 e Gradle 8.9; o Wrapper baixa o Gradle na primeira execução.

1. Clone o **seu fork** e abra a pasta no Android Studio, ou use o terminal.
2. Configure `ANDROID_HOME` para o SDK ou crie `local.properties` com `sdk.dir=/caminho/do/android-sdk`. O Android Studio costuma criar esse arquivo. Não o commite.
3. Execute os comandos abaixo na raiz.

Linux / macOS / Git Bash:

```bash
./gradlew :app:testDebugUnitTest :app:assembleDebug
```

Windows PowerShell:

```powershell
.\gradlew.bat :app:testDebugUnitTest :app:assembleDebug
```

Saídas:

- APK: `app/build/outputs/apk/debug/app-debug.apk`.
- Relatório HTML: `app/build/reports/tests/testDebugUnitTest/index.html`.
- Relatórios JUnit XML: `app/build/test-results/testDebugUnitTest/`.

## Onde mexer

| Arquivo | Responsabilidade |
|---|---|
| [strings.xml](app/src/main/res/values/strings.xml) | Textos exibidos no aplicativo |
| [activity_main.xml](app/src/main/res/layout/activity_main.xml) | Tela do contador |
| [MainActivity.java](app/src/main/java/dev/vmenezes/codemagic/android/MainActivity.java) | Cliques, renderização e estado da tela |
| [Counter.java](app/src/main/java/dev/vmenezes/codemagic/android/Counter.java) | Regra de incremento e reset |
| [CounterTest.java](app/src/test/java/dev/vmenezes/codemagic/android/CounterTest.java) | Quatro testes da regra do contador |
| [codemagic.yaml](codemagic.yaml) | Ambiente, gatilho, testes, build e artefatos |
| [docs/](docs/) | Roteiro, quiz, tarefa e modelo de entrega |

## Problemas frequentes

| Sintoma | O que verificar |
|---|---|
| `SDK location not found` localmente | `ANDROID_HOME` ou `sdk.dir` no `local.properties` |
| `Permission denied` no Wrapper | Execute `chmod +x gradlew` em Linux/macOS |
| Java ou Gradle incompatível | Use JDK 17 e os arquivos do Wrapper versionados |
| Teste falhou e não apareceu APK | Abra o relatório; corrija o código antes de repetir |
| Push não iniciou execução | Fork correto, branch e webhook conectado no Codemagic |
| Build ainda rodando durante a aula | Continue pelos logs ou use uma execução anterior preparada |
| Não foi possível instalar o APK | Android compatível, permissão de instalação e assinatura da versão anterior |

## Referências

- [Android nativo no Codemagic](https://docs.codemagic.io/yaml-quick-start/building-a-native-android-app/)
- [Configuração YAML](https://docs.codemagic.io/yaml-basic-configuration/yaml-getting-started/)
- [Relatórios de testes](https://docs.codemagic.io/yaml-testing/testing/)
- [Gatilhos automáticos](https://docs.codemagic.io/yaml-running-builds/starting-builds-automatically/)
- [Compatibilidade do Android Gradle Plugin 8.7](https://developer.android.com/build/releases/agp-8-7-0-release-notes)

Licença [MIT](LICENSE).
