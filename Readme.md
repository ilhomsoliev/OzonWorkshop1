# Workshop Clean+MVVM+Jetpack
## 1. Инициализация проекта - 10 мин
Требование к проекту:
 - Kotlin версии 1.6.20
 - Android Gradle Plugin версии 7.1.3
 - Android Studio Android Studio Bumblebee | 2021.1.1 Patch 3
 - Готовый эмулятор Android 10

Клонируем проект с помощью команды:
> git clone https://gitlab.ozon.dev/android/classroom-2/workshop-1.git

В проекте имеется папочка sources там вы найдете необходимые файлики и модельки, которые нужно будет по ходу воркшопа.

После того как проект затащили нужно будет его скомпилировать и запустить на эмуляторе.

## 2. Q&A - 5 мин

## 3. Data слой - 10 мин
Давайте создадим папочку *data* на одном уровне с папкой *source*. В папке *data* нужно будет создать две папки:
- *dto* (Для хранение объектов, которыми обмениваются бэкенд и приложение. Там могут быть и объекты для хранения в базу данных.)
- *repositoriesImpl* (Для реализации репозитория интерфейс которого будет лежать в слое domain)

Перетащите файл `ProductInListDTO` в папку dto из папки sources, а файл `mock.kt` в *repositoryImpl*.

## 4. Q&A - 5 мин

## 5. Domain слой - 20 мин
Создадим папочку *domain* на одном уровне с папкой *data*. В папке *domain* нужно будет создать две папки:
- *repositories* (для хранения интерфейсов repository имплементация которых лежит в слое data)
- *interactors* (для хранение интерфейса и реализации интеракторов, которые будут передавать данные из domain слоя в data слой)

Нужно будет создать интерфейс ProductsRepository в папке *repositories* с тремя методами:
> fun getProducts(): List<ProductInListVO>

> fun getProductById(guid: String): ProductInListVO

Модель данных `ProductInListVO`, это данные о товаре для отображения в слое Presentation (т.е. для View). 
Теперь нужно будет сделать реализацию `ProductsRepository` c названием класса `MockProductsRepositoryImpl` в папке *data/repositoriesImpl*.
Для простоты, маппинг данных будем производить в реализации самого `MockProductsRepositoryImpl`, данные для маппинга хранятся в файлике *mock.kt*. 
Сам маппер написан ввиде `extension` функции в файлике *ProductsMapper.kt*. 

И наконец нужно будет создать интерфейс `ProductsInteractor` с реализацией в папке *interactors*. Класс `ProductsInteractorImpl` должен  принимать в качестве аргумента `ProductsRepository`.
Интерфейс `ProductsInteractor` должен содержать методы:
> fun getProducts(): List<ProductInListVO>

> fun getProductById(guid: String): ProductInListVO

Теперь создадим папочку *di* на уровне папки *data* и переместим *sources/ServiceLocator* в созданную папку. После перемещения нужно будет зайти в `ServiceLocator` и раскоментировать все.
Поправьте все конфликты и запустите проект.

> P.S. Так как мы еще не прошли тему по Dependencies Injection, поэтому используем ServiceLocator. 

> Подробнее можете почитать [тут](http://sergeyteplyakov.blogspot.com/2013/03/di-service-locator.html), [тут](https://habr.com/ru/post/465395/) и [тут](https://javatutor.net/articles/j2ee-pattern-service-locator).

## 6. Presentation слой - 5 мин
Так как этот слой по больше, разделим его на несколько частей. Перед тем как мы начнем реализовывать экраны, нужно соверщить следующие действия:
1. Создадим папку presentation на одном уровне с папкой *data* и внутри создадим еще папки:
    1. view
    2. viewModel
    3. viewObject
2. Нужно будет переместить файл `ViewModelFactory.kt` в созданную папку *viewModel*.
3. Переместите файл `ProductInListVO.kt` в папку *viewObject*.

### 6.1. Экран со списком заказов - 30 мин
В `activity_main.xml` уже лежит `FragmentContainerView`, подребнее можно почитать [тут](https://developer.android.com/reference/androidx/fragment/app/FragmentContainerView).
- Создадим фрагмент `ProductsFragment` в папке *view*
- Добавляем аттрибут `android:name` в `FragmentContainerView`, который находится в `activity_main.xml`, и указываем в нём наш фрагмент `ProductsFragment`, 
  чтобы по умолчанию в `FragmentContainerView` добавлялся наш созданный фрагмент.
- Создаем recyclerView в xml фрагмента. Разметку для item RecyclerView лежит в `product_list_item.xml` уже в ресурсах, то есть в папке *res/layout*.
- Создаем адаптер для нашего RecyclerView и подключаем адаптер к RecyclerView. Не забудьте создать `ViewHolder` и забиндить данные. 
  Не забудьте подключить [Glide](https://github.com/bumptech/glide#how-do-i-use-glide), для биндинга фото.
- Создаем `ProductsViewModel`, который в качестве аргумента в конструторе принимает `ProductsInteractor` и наследуясь от `ViewModel`.
- В `ProductsViewModel` создаем приватную переменную `_productLD` с типом `MutableLiveData` 
  и создаем публичную переменную `productLD` с типом `LiveData`, который берет значение из `_productLD`.
- Далее нужно будет в блоке `init` нашего `ProductsViewModel` получить из `ProductsInteractor` список товаров и положить в `_productLD`.
- Во фрагменте нужно будет создать переменную `viewModel: ProductsViewModel`
  ```
    private val vm: ProductsViewModel by viewModelCreator {
      ProductsViewModel(ServiceLocator().productsInteractor)
    }
  ```
- В методе `onViewCreated` нашего фрагмента нужно будет подписаться на `productLD` и заполнить полученными данными наш RecyclerView.
- Компилирем проект и запускаем, у нас должен получится экран со списком заказов.

### 6.2. Q&A - 10 мин

### 6.3. Экран с подробной информацией о заказе - 30 мин
- Созадаем обычный Fragment, назовем его `PDPFragment`, он должен получать аргумент (`productId`, т.е. Id товара) из `bundle`. 
  Ui у фрагмента уже имеется в `pdp_fragment.xml`.
- Нужно будет открыть этот фрагмент при нажатии на товар в `ProductsFragment`, передав `Id` товара.
- Теперь нужно будет создать для нашего фрагмента `ViewModel` и достать по `Id` товара информацию о товаре. Мы уже ранее это делали в предыдушем этапе.
- А теперь нужно будет забиндить полученные данные о товаре, чтобы отобразилось на экране.
- Компилируем проект и проверяем, что все работает и все отображается по нашему экрану.

### 6.4. Q&A - 10 мин

### 7. Доработка приложения - 20 мин
Дорабатываем приложения, ждем тех кто еще не успел все сделать и задаем вопросы по воркшопу