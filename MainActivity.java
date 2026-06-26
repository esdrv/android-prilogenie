package com.example.a123123;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    private final int PRIMARY = Color.rgb(20, 32, 86);
    private final int PRIMARY_2 = Color.rgb(31, 52, 135);
    private final int DARK = Color.rgb(8, 15, 45);
    private final int ORANGE = Color.rgb(255, 111, 0);
    private final int ORANGE_LIGHT = Color.rgb(255, 243, 224);
    private final int BG = Color.rgb(244, 246, 250);
    private final int CARD = Color.WHITE;
    private final int TEXT = Color.rgb(24, 28, 48);
    private final int MUTED = Color.rgb(102, 112, 133);
    private final int BORDER = Color.rgb(222, 226, 234);
    private final int GREEN = Color.rgb(35, 134, 84);
    private final int GREEN_LIGHT = Color.rgb(232, 245, 233);
    private final int RED = Color.rgb(211, 47, 47);
    private final int BLUE_LIGHT = Color.rgb(232, 236, 255);

    private final List<Product> products = new ArrayList<>();
    private final List<CartItem> cart = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<Integer> favorites = new ArrayList<>();

    private String selectedCategory = "Все";
    private String searchQuery = "";
    private String sortMode = "Популярные";
    private String userName = "Гость";
    private int promoPercent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(DARK);
        seedProducts();
        showLoginScreen();
    }

    private void seedProducts() {
        products.clear();
        products.add(new Product(1, "Тормозные колодки Brembo", "TK-2241", "Передние колодки для LADA и Renault", "Премиальные тормозные колодки с хорошим ресурсом и стабильным торможением в городе.", 2490, "🛞", "Тормоза", "Brembo", 4.8, 12, 1, "LADA Vesta, Granta, Renault Logan", "Хит"));
        products.add(new Product(2, "Масляный фильтр Mann", "MF-5501", "Фильтр для двигателей 1.4–2.0L", "Надёжная фильтрация масла, защита двигателя от мелких частиц и грязи.", 650, "🔩", "Двигатель", "Mann", 4.7, 33, 1, "Toyota, Hyundai, KIA, Mazda", "В наличии"));
        products.add(new Product(3, "Свечи зажигания NGK", "SZ-8812", "Комплект 4 шт. Улучшенный запуск", "Свечи помогают двигателю запускаться ровнее, снижают пропуски зажигания и расход топлива.", 1120, "⚡", "Двигатель", "NGK", 4.6, 18, 2, "LADA, Toyota, Nissan, Hyundai", "Комплект"));
        products.add(new Product(4, "Амортизатор KYB Excel-G", "AM-3301", "Передний газомасляный амортизатор", "Улучшает устойчивость автомобиля, снижает раскачку и делает поездку мягче.", 3850, "⚙️", "Подвеска", "KYB", 4.9, 7, 2, "Renault Logan, Sandero, Largus", "Топ"));
        products.add(new Product(5, "Ремень ГРМ Gates", "RG-7701", "Усиленный ремень PowerGrip", "Ремень ГРМ с увеличенным ресурсом. Важная деталь для стабильной работы двигателя.", 1890, "🔧", "Двигатель", "Gates", 4.8, 10, 1, "Volkswagen, Skoda, Ford, Audi", "Ресурс"));
        products.add(new Product(6, "Воздушный фильтр Bosch", "VF-4420", "Фильтр воздуха для VW / Skoda", "Задерживает пыль и грязь, чтобы двигатель получал более чистый воздух.", 780, "💨", "Фильтры", "Bosch", 4.5, 25, 1, "VW Golf, Polo, Passat, Skoda", "Бюджет"));
        products.add(new Product(7, "Аккумулятор Varta 60Ah", "AK-6031", "Blue Dynamic 60Ah 540A", "Аккумулятор для уверенного запуска автомобиля в холодную погоду.", 8990, "🔋", "Электрика", "Varta", 4.7, 4, 2, "Большинство легковых авто", "Мощный"));
        products.add(new Product(8, "Рулевая тяга Lemfoerder", "RT-1105", "Точная рулевая тяга", "Помогает убрать люфт и сохранить точность управления автомобилем.", 2150, "🚗", "Рулевое", "Lemfoerder", 4.6, 9, 1, "Ford Focus, Mondeo", "Надёжно"));
        products.add(new Product(9, "Лампа Philips H7", "LP-7007", "Галогенная лампа ближнего света", "Яркий и ровный свет для безопасной езды вечером и ночью.", 540, "💡", "Электрика", "Philips", 4.4, 41, 1, "Авто с цоколем H7", "Недорого"));
        products.add(new Product(10, "Антифриз Sintec G12", "AF-1200", "Красный антифриз 5 литров", "Защищает систему охлаждения от перегрева, коррозии и замерзания.", 960, "🧴", "Жидкости", "Sintec", 4.5, 17, 2, "Бензиновые и дизельные двигатели", "5 литров"));
        products.add(new Product(11, "Моторное масло Shell 5W-40", "MO-5400", "Синтетическое масло 4 литра", "Подходит для многих современных двигателей, снижает износ и шум работы мотора.", 3190, "🛢️", "Жидкости", "Shell", 4.8, 14, 1, "Бензин / дизель", "Популярно"));
        products.add(new Product(12, "Комплект дворников Bosch", "DW-3310", "Бескаркасные щётки 600/450", "Хорошо очищают стекло, не скрипят и подходят для ежедневной эксплуатации.", 1490, "🌧️", "Кузов", "Bosch", 4.6, 20, 1, "Универсальный крепёж", "Сезон"));
    }

    private void showLoginScreen() {
        LinearLayout root = vertical();
        root.setGravity(Gravity.CENTER);
        root.setPadding(dp(26), dp(26), dp(26), dp(26));
        root.setBackground(gradient(DARK, PRIMARY_2, 0));

        TextView logo = text("⚙️", 58, Color.WHITE, true);
        logo.setGravity(Gravity.CENTER);
        root.addView(logo, matchWrap());

        TextView title = text("DriveParts Pro", 32, Color.WHITE, true);
        title.setGravity(Gravity.CENTER);
        root.addView(title, matchWrap());

        TextView subtitle = text("продвинутый магазин автозапчастей", 14, Color.rgb(220, 225, 255), false);
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle, matchWrap());

        root.addView(space(28));

        LinearLayout card = card(22, CARD);
        card.setPadding(dp(22), dp(22), dp(22), dp(22));
        root.addView(card, new LinearLayout.LayoutParams(-1, -2));

        TextView enter = text("Вход в аккаунт", 22, TEXT, true);
        card.addView(enter, matchWrap());
        card.addView(smallText("В демо-версии: admin / 1234"));
        card.addView(space(16));

        EditText login = input("Логин", "👤");
        login.setText("admin");
        card.addView(login, matchWrap());
        card.addView(space(12));

        EditText password = input("Пароль", "🔒");
        password.setText("1234");
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        card.addView(password, matchWrap());
        card.addView(space(18));

        Button btn = primaryButton("Войти");
        card.addView(btn, matchWrap());

        TextView error = text("", 13, RED, true);
        error.setGravity(Gravity.CENTER);
        card.addView(error, matchWrap());

        btn.setOnClickListener(v -> {
            if (login.getText().toString().trim().isEmpty() || password.getText().toString().isEmpty()) {
                error.setText("Заполните логин и пароль");
            } else if (login.getText().toString().trim().equals("admin") && password.getText().toString().equals("1234")) {
                userName = "Администратор";
                Toast.makeText(this, "Добро пожаловать!", Toast.LENGTH_SHORT).show();
                showHomeScreen();
            } else {
                error.setText("Неверный логин или пароль");
            }
        });

        setContentView(root);
    }

    private void showHomeScreen() {
        LinearLayout root = vertical();
        root.setBackgroundColor(BG);
        root.addView(topBar("DriveParts Pro", "Каталог и быстрый заказ", null));

        ScrollView scroll = new ScrollView(this);
        LinearLayout content = vertical();
        content.setPadding(dp(14), dp(14), dp(14), dp(90));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        content.addView(heroBlock());
        content.addView(space(12));
        content.addView(searchPanel());
        content.addView(space(12));
        content.addView(categoryPanel());
        content.addView(space(8));
        content.addView(sortPanel());
        content.addView(space(10));

        List<Product> filtered = getFilteredProducts();
        TextView count = smallText("Найдено: " + filtered.size() + " товаров");
        content.addView(count, matchWrap());
        content.addView(space(6));

        if (filtered.isEmpty()) {
            content.addView(emptyCard("Ничего не найдено", "Попробуй изменить поиск или категорию"));
        } else {
            for (Product p : filtered) {
                content.addView(productCard(p));
                content.addView(space(12));
            }
        }

        root.addView(bottomNav("catalog"));
        setContentView(root);
    }

    private LinearLayout heroBlock() {
        LinearLayout hero = card(20, PRIMARY);
        hero.setPadding(dp(18), dp(18), dp(18), dp(18));
        hero.setBackground(gradient(PRIMARY, PRIMARY_2, 35));

        TextView big = text("🚘 Быстрый подбор деталей", 20, Color.WHITE, true);
        hero.addView(big, matchWrap());
        hero.addView(space(6));
        TextView desc = text("Каталог, избранное, корзина, промокод и история заказов — всё в одном демо-приложении.", 13, Color.rgb(232, 236, 255), false);
        hero.addView(desc, matchWrap());
        hero.addView(space(14));

        LinearLayout row = horizontal();
        row.setGravity(Gravity.CENTER);
        row.addView(statBox(products.size() + "", "товаров"), new LinearLayout.LayoutParams(0, -2, 1));
        row.addView(spaceW(8));
        row.addView(statBox(cartCount() + "", "в корзине"), new LinearLayout.LayoutParams(0, -2, 1));
        row.addView(spaceW(8));
        row.addView(statBox(orders.size() + "", "заказов"), new LinearLayout.LayoutParams(0, -2, 1));
        hero.addView(row, matchWrap());
        return hero;
    }

    private LinearLayout statBox(String value, String label) {
        LinearLayout box = vertical();
        box.setPadding(dp(10), dp(10), dp(10), dp(10));
        box.setGravity(Gravity.CENTER);
        box.setBackground(round(Color.argb(48, 255, 255, 255), 16));
        TextView v = text(value, 20, Color.WHITE, true);
        v.setGravity(Gravity.CENTER);
        TextView l = text(label, 11, Color.rgb(232, 236, 255), false);
        l.setGravity(Gravity.CENTER);
        box.addView(v, matchWrap());
        box.addView(l, matchWrap());
        return box;
    }

    private LinearLayout searchPanel() {
        LinearLayout box = card(18, CARD);
        box.setPadding(dp(14), dp(14), dp(14), dp(14));
        box.addView(text("Поиск", 16, TEXT, true), matchWrap());
        box.addView(space(8));

        EditText search = input("Название, бренд или артикул", "🔎");
        search.setText(searchQuery);
        box.addView(search, matchWrap());
        box.addView(space(10));

        Button apply = secondaryButton("Найти");
        box.addView(apply, matchWrap());
        apply.setOnClickListener(v -> {
            searchQuery = search.getText().toString().trim();
            showHomeScreen();
        });
        return box;
    }

    private View categoryPanel() {
        HorizontalScrollView hsv = new HorizontalScrollView(this);
        hsv.setHorizontalScrollBarEnabled(false);
        LinearLayout row = horizontal();
        row.setPadding(dp(2), dp(2), dp(2), dp(2));
        hsv.addView(row);
        for (String c : categories()) {
            Button chip = chip(c, c.equals(selectedCategory));
            chip.setOnClickListener(v -> {
                selectedCategory = ((Button) v).getText().toString();
                showHomeScreen();
            });
            row.addView(chip);
            row.addView(spaceW(8));
        }
        return hsv;
    }

    private View sortPanel() {
        HorizontalScrollView hsv = new HorizontalScrollView(this);
        hsv.setHorizontalScrollBarEnabled(false);
        LinearLayout row = horizontal();
        String[] sorts = {"Популярные", "Дешевле", "Дороже", "Рейтинг"};
        for (String s : sorts) {
            Button b = chip(s, s.equals(sortMode));
            b.setOnClickListener(v -> {
                sortMode = ((Button) v).getText().toString();
                showHomeScreen();
            });
            row.addView(b);
            row.addView(spaceW(8));
        }
        hsv.addView(row);
        return hsv;
    }

    private View productCard(Product p) {
        LinearLayout card = card(20, CARD);
        card.setPadding(dp(14), dp(14), dp(14), dp(14));

        LinearLayout top = horizontal();
        top.setGravity(Gravity.CENTER_VERTICAL);

        TextView pic = text(p.icon, 42, TEXT, false);
        pic.setGravity(Gravity.CENTER);
        pic.setBackground(round(BLUE_LIGHT, 18));
        top.addView(pic, new LinearLayout.LayoutParams(dp(74), dp(74)));
        top.addView(spaceW(12));

        LinearLayout info = vertical();
        TextView name = text(p.name, 16, TEXT, true);
        name.setMaxLines(2);
        info.addView(name, matchWrap());
        info.addView(smallText(p.brand + " • " + p.sku));
        info.addView(space(4));
        info.addView(label(p.label + "  ⭐ " + p.rating, ORANGE_LIGHT, ORANGE));
        top.addView(info, new LinearLayout.LayoutParams(0, -2, 1));

        Button fav = smallButton(isFavorite(p.id) ? "♥" : "♡");
        fav.setTextColor(isFavorite(p.id) ? RED : MUTED);
        fav.setOnClickListener(v -> {
            toggleFavorite(p.id);
            showHomeScreen();
        });
        top.addView(fav, new LinearLayout.LayoutParams(dp(48), dp(48)));
        card.addView(top, matchWrap());

        card.addView(space(10));
        TextView desc = text(p.shortDesc, 13, MUTED, false);
        desc.setMaxLines(2);
        card.addView(desc, matchWrap());
        card.addView(space(10));

        LinearLayout mid = horizontal();
        mid.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout priceBlock = vertical();
        priceBlock.addView(text(price(p.price) + " ₽", 22, ORANGE, true), matchWrap());
        priceBlock.addView(text("✓ В наличии: " + p.stock + " шт.", 12, GREEN, false), matchWrap());
        mid.addView(priceBlock, new LinearLayout.LayoutParams(0, -2, 1));
        mid.addView(deliveryBox(p.deliveryDays));
        card.addView(mid, matchWrap());

        card.addView(space(12));
        LinearLayout actions = horizontal();
        Button details = secondaryButton("Подробнее");
        details.setOnClickListener(v -> showDetailsScreen(p));
        actions.addView(details, new LinearLayout.LayoutParams(0, dp(48), 1));
        actions.addView(spaceW(10));
        Button add = primaryButton("В корзину");
        add.setOnClickListener(v -> {
            addToCart(p, 1);
            Toast.makeText(this, "Добавлено: " + p.name, Toast.LENGTH_SHORT).show();
            showHomeScreen();
        });
        actions.addView(add, new LinearLayout.LayoutParams(0, dp(48), 1));
        card.addView(actions, matchWrap());
        return card;
    }

    private View deliveryBox(int days) {
        LinearLayout box = vertical();
        box.setGravity(Gravity.CENTER);
        box.setPadding(dp(10), dp(7), dp(10), dp(7));
        box.setBackground(round(GREEN_LIGHT, 14));
        box.addView(text("🚚", 18, GREEN, false), matchWrap());
        TextView t = text(shortDelivery(days), 11, GREEN, true);
        t.setGravity(Gravity.CENTER);
        box.addView(t, matchWrap());
        return box;
    }

    private void showDetailsScreen(Product p) {
        LinearLayout root = vertical();
        root.setBackgroundColor(BG);
        root.addView(topBar("Карточка товара", p.sku, () -> showHomeScreen()));

        ScrollView scroll = new ScrollView(this);
        LinearLayout content = vertical();
        content.setPadding(dp(14), dp(14), dp(14), dp(90));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        LinearLayout hero = card(22, CARD);
        hero.setPadding(0, 0, 0, dp(16));
        TextView image = text(p.icon, 96, Color.WHITE, false);
        image.setGravity(Gravity.CENTER);
        image.setBackground(gradient(PRIMARY, PRIMARY_2, 35));
        hero.addView(image, new LinearLayout.LayoutParams(-1, dp(190)));
        LinearLayout body = vertical();
        body.setPadding(dp(16), dp(16), dp(16), 0);
        body.addView(text(p.name, 23, TEXT, true), matchWrap());
        body.addView(smallText(p.brand + " • Артикул: " + p.sku));
        body.addView(space(10));
        body.addView(text(price(p.price) + " ₽", 26, ORANGE, true), matchWrap());
        body.addView(space(10));
        body.addView(text(p.fullDesc, 14, MUTED, false), matchWrap());
        hero.addView(body, matchWrap());
        content.addView(hero, matchWrap());
        content.addView(space(12));

        LinearLayout info = card(18, CARD);
        info.setPadding(dp(16), dp(16), dp(16), dp(16));
        info.addView(text("Характеристики", 18, TEXT, true), matchWrap());
        info.addView(space(10));
        info.addView(infoRow("Категория", p.category));
        info.addView(infoRow("Совместимость", p.compatibility));
        info.addView(infoRow("Рейтинг", "⭐ " + p.rating));
        info.addView(infoRow("Доставка", fullDelivery(p.deliveryDays)));
        info.addView(infoRow("Остаток", p.stock + " шт."));
        content.addView(info, matchWrap());
        content.addView(space(12));

        LinearLayout panel = card(18, CARD);
        panel.setPadding(dp(16), dp(16), dp(16), dp(16));
        panel.addView(text("Действия", 18, TEXT, true), matchWrap());
        panel.addView(space(12));
        LinearLayout actions = horizontal();
        Button fav = secondaryButton(isFavorite(p.id) ? "Убрать ♥" : "В избранное ♡");
        fav.setOnClickListener(v -> {
            toggleFavorite(p.id);
            showDetailsScreen(p);
        });
        actions.addView(fav, new LinearLayout.LayoutParams(0, dp(52), 1));
        actions.addView(spaceW(10));
        Button add = primaryButton("В корзину");
        add.setOnClickListener(v -> {
            addToCart(p, 1);
            Toast.makeText(this, "Товар добавлен", Toast.LENGTH_SHORT).show();
            showCartScreen();
        });
        actions.addView(add, new LinearLayout.LayoutParams(0, dp(52), 1));
        panel.addView(actions, matchWrap());
        content.addView(panel, matchWrap());

        root.addView(bottomNav("catalog"));
        setContentView(root);
    }

    private View infoRow(String key, String value) {
        LinearLayout row = horizontal();
        row.setPadding(0, dp(7), 0, dp(7));
        TextView k = text(key, 13, MUTED, false);
        TextView v = text(value, 13, TEXT, true);
        v.setGravity(Gravity.RIGHT);
        row.addView(k, new LinearLayout.LayoutParams(0, -2, 1));
        row.addView(v, new LinearLayout.LayoutParams(0, -2, 1));
        return row;
    }

    private void showCartScreen() {
        LinearLayout root = vertical();
        root.setBackgroundColor(BG);
        root.addView(topBar("Корзина", cartCount() + " товаров", () -> showHomeScreen()));

        ScrollView scroll = new ScrollView(this);
        LinearLayout content = vertical();
        content.setPadding(dp(14), dp(14), dp(14), dp(90));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        if (cart.isEmpty()) {
            content.addView(emptyCard("Корзина пустая", "Добавь товар из каталога, чтобы оформить заказ"));
        } else {
            for (CartItem item : cart) {
                content.addView(cartItemView(item));
                content.addView(space(10));
            }
            content.addView(promoBlock());
            content.addView(space(12));
            content.addView(summaryBlock("Оформить заказ", () -> showCheckoutScreen()));
        }

        root.addView(bottomNav("cart"));
        setContentView(root);
    }

    private View cartItemView(CartItem item) {
        Product p = item.product;
        LinearLayout card = card(18, CARD);
        card.setPadding(dp(14), dp(14), dp(14), dp(14));

        LinearLayout top = horizontal();
        top.setGravity(Gravity.CENTER_VERTICAL);
        TextView pic = text(p.icon, 32, TEXT, false);
        pic.setGravity(Gravity.CENTER);
        pic.setBackground(round(BLUE_LIGHT, 14));
        top.addView(pic, new LinearLayout.LayoutParams(dp(58), dp(58)));
        top.addView(spaceW(12));
        LinearLayout info = vertical();
        info.addView(text(p.name, 15, TEXT, true), matchWrap());
        info.addView(smallText(fullDelivery(p.deliveryDays)));
        top.addView(info, new LinearLayout.LayoutParams(0, -2, 1));
        Button del = smallButton("✕");
        del.setTextColor(RED);
        del.setOnClickListener(v -> {
            cart.remove(item);
            showCartScreen();
        });
        top.addView(del, new LinearLayout.LayoutParams(dp(46), dp(46)));
        card.addView(top, matchWrap());
        card.addView(space(12));

        LinearLayout bottom = horizontal();
        bottom.setGravity(Gravity.CENTER_VERTICAL);
        bottom.addView(text(price(p.price * item.quantity) + " ₽", 20, ORANGE, true), new LinearLayout.LayoutParams(0, -2, 1));
        Button minus = smallButton("−");
        minus.setOnClickListener(v -> changeQuantity(p, -1));
        bottom.addView(minus, new LinearLayout.LayoutParams(dp(42), dp(42)));
        TextView qty = text(String.valueOf(item.quantity), 18, TEXT, true);
        qty.setGravity(Gravity.CENTER);
        bottom.addView(qty, new LinearLayout.LayoutParams(dp(48), -2));
        Button plus = smallButton("+");
        plus.setOnClickListener(v -> changeQuantity(p, 1));
        bottom.addView(plus, new LinearLayout.LayoutParams(dp(42), dp(42)));
        card.addView(bottom, matchWrap());
        return card;
    }

    private View promoBlock() {
        LinearLayout block = card(18, CARD);
        block.setPadding(dp(16), dp(16), dp(16), dp(16));
        block.addView(text("Промокод", 18, TEXT, true), matchWrap());
        block.addView(smallText("Доступно: AUTO10 или START5"));
        block.addView(space(10));
        EditText promo = input("Введите промокод", "🎁");
        block.addView(promo, matchWrap());
        block.addView(space(10));
        Button apply = secondaryButton("Применить");
        block.addView(apply, matchWrap());
        apply.setOnClickListener(v -> {
            String code = promo.getText().toString().trim().toUpperCase(Locale.ROOT);
            if (code.equals("AUTO10")) {
                promoPercent = 10;
                Toast.makeText(this, "Скидка 10% применена", Toast.LENGTH_SHORT).show();
            } else if (code.equals("START5")) {
                promoPercent = 5;
                Toast.makeText(this, "Скидка 5% применена", Toast.LENGTH_SHORT).show();
            } else {
                promoPercent = 0;
                Toast.makeText(this, "Промокод не найден", Toast.LENGTH_SHORT).show();
            }
            showCartScreen();
        });
        if (promoPercent > 0) {
            block.addView(space(8));
            block.addView(label("Сейчас активна скидка " + promoPercent + "%", GREEN_LIGHT, GREEN));
        }
        return block;
    }

    private View summaryBlock(String buttonText, Runnable action) {
        LinearLayout block = card(20, CARD);
        block.setPadding(dp(16), dp(16), dp(16), dp(16));
        int sum = cartTotal();
        int discount = sum * promoPercent / 100;
        int total = sum - discount;
        block.addView(summaryRow("Сумма товаров", price(sum) + " ₽", false));
        if (promoPercent > 0) block.addView(summaryRow("Скидка " + promoPercent + "%", "−" + price(discount) + " ₽", false));
        block.addView(summaryRow("Итого", price(total) + " ₽", true));
        block.addView(space(14));
        Button b = primaryButton(buttonText);
        b.setOnClickListener(v -> action.run());
        block.addView(b, new LinearLayout.LayoutParams(-1, dp(54)));
        return block;
    }

    private View summaryRow(String left, String right, boolean big) {
        LinearLayout row = horizontal();
        row.setPadding(0, dp(5), 0, dp(5));
        row.addView(text(left, big ? 18 : 14, big ? TEXT : MUTED, big), new LinearLayout.LayoutParams(0, -2, 1));
        TextView r = text(right, big ? 22 : 14, big ? ORANGE : TEXT, true);
        r.setGravity(Gravity.RIGHT);
        row.addView(r, new LinearLayout.LayoutParams(0, -2, 1));
        return row;
    }

    private void showCheckoutScreen() {
        LinearLayout root = vertical();
        root.setBackgroundColor(BG);
        root.addView(topBar("Оформление", "Самовывоз из магазина", () -> showCartScreen()));

        ScrollView scroll = new ScrollView(this);
        LinearLayout content = vertical();
        content.setPadding(dp(14), dp(14), dp(14), dp(90));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        LinearLayout form = card(18, CARD);
        form.setPadding(dp(16), dp(16), dp(16), dp(16));
        form.addView(text("Данные покупателя", 18, TEXT, true), matchWrap());
        form.addView(space(10));
        EditText name = input("Имя", "👤");
        name.setText(userName.equals("Администратор") ? "Братишка" : userName);
        EditText phone = input("Телефон", "☎️");
        phone.setInputType(InputType.TYPE_CLASS_PHONE);
        EditText comment = input("Комментарий к заказу", "💬");
        form.addView(name, matchWrap());
        form.addView(space(10));
        form.addView(phone, matchWrap());
        form.addView(space(10));
        form.addView(comment, matchWrap());
        content.addView(form, matchWrap());
        content.addView(space(12));

        LinearLayout delivery = card(18, CARD);
        delivery.setPadding(dp(16), dp(16), dp(16), dp(16));
        delivery.addView(text("Получение", 18, TEXT, true), matchWrap());
        delivery.addView(space(10));
        delivery.addView(label("📍 Магазин: ул. Центральная, 10", BLUE_LIGHT, PRIMARY));
        delivery.addView(space(8));
        delivery.addView(label("📦 Доставка: " + fullDelivery(maxDeliveryDays()), GREEN_LIGHT, GREEN));
        delivery.addView(space(8));
        delivery.addView(smallText("После поступления менеджер позвонит и подтвердит выдачу товара."));
        content.addView(delivery, matchWrap());
        content.addView(space(12));

        content.addView(summaryBlock("Подтвердить заказ", () -> {
            if (name.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phone.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Введите телефон", Toast.LENGTH_SHORT).show();
                return;
            }
            createOrder(name.getText().toString().trim(), phone.getText().toString().trim());
        }));

        root.addView(bottomNav("cart"));
        setContentView(root);
    }

    private void createOrder(String name, String phone) {
        int sum = cartTotal();
        int total = sum - (sum * promoPercent / 100);
        String number = "DP-" + String.valueOf(System.currentTimeMillis()).substring(7);
        StringBuilder items = new StringBuilder();
        for (CartItem item : cart) {
            items.append(item.product.name).append(" × ").append(item.quantity).append("\n");
        }
        orders.add(0, new Order(number, name, phone, items.toString().trim(), total, fullDelivery(maxDeliveryDays())));
        cart.clear();
        promoPercent = 0;
        showSuccessScreen(number, total);
    }

    private void showSuccessScreen(String number, int total) {
        LinearLayout root = vertical();
        root.setGravity(Gravity.CENTER);
        root.setPadding(dp(24), dp(24), dp(24), dp(24));
        root.setBackgroundColor(BG);

        LinearLayout card = card(24, CARD);
        card.setPadding(dp(24), dp(24), dp(24), dp(24));
        card.setGravity(Gravity.CENTER_HORIZONTAL);
        root.addView(card, matchWrap());

        TextView ok = text("✅", 70, GREEN, false);
        ok.setGravity(Gravity.CENTER);
        card.addView(ok, matchWrap());
        TextView title = text("Заказ оформлен", 25, TEXT, true);
        title.setGravity(Gravity.CENTER);
        card.addView(title, matchWrap());
        TextView numberView = smallText("Номер: " + number);
        numberView.setGravity(Gravity.CENTER);
        card.addView(numberView, matchWrap());
        card.addView(space(12));
        TextView sum = text("Сумма: " + price(total) + " ₽", 20, ORANGE, true);
        sum.setGravity(Gravity.CENTER);
        card.addView(sum, matchWrap());
        card.addView(space(14));
        TextView note = text("Товар будет доставлен в магазин. Историю заказов можно посмотреть в профиле.", 14, MUTED, false);
        note.setGravity(Gravity.CENTER);
        card.addView(note, matchWrap());
        card.addView(space(20));
        Button b = primaryButton("Вернуться в каталог");
        b.setOnClickListener(v -> showHomeScreen());
        card.addView(b, new LinearLayout.LayoutParams(-1, dp(54)));
        setContentView(root);
    }

    private void showFavoritesScreen() {
        LinearLayout root = vertical();
        root.setBackgroundColor(BG);
        root.addView(topBar("Избранное", favorites.size() + " товаров", () -> showHomeScreen()));
        ScrollView scroll = new ScrollView(this);
        LinearLayout content = vertical();
        content.setPadding(dp(14), dp(14), dp(14), dp(90));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        if (favorites.isEmpty()) {
            content.addView(emptyCard("Пока пусто", "Нажимай ♡ у товара, чтобы сохранить его сюда"));
        } else {
            for (Product p : products) {
                if (isFavorite(p.id)) {
                    content.addView(productCard(p));
                    content.addView(space(12));
                }
            }
        }
        root.addView(bottomNav("fav"));
        setContentView(root);
    }

    private void showProfileScreen() {
        LinearLayout root = vertical();
        root.setBackgroundColor(BG);
        root.addView(topBar("Профиль", userName, () -> showHomeScreen()));

        ScrollView scroll = new ScrollView(this);
        LinearLayout content = vertical();
        content.setPadding(dp(14), dp(14), dp(14), dp(90));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        LinearLayout profile = card(20, CARD);
        profile.setPadding(dp(18), dp(18), dp(18), dp(18));
        profile.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView avatar = text("👨‍🔧", 64, TEXT, false);
        avatar.setGravity(Gravity.CENTER);
        profile.addView(avatar, matchWrap());
        TextView name = text(userName, 22, TEXT, true);
        name.setGravity(Gravity.CENTER);
        profile.addView(name, matchWrap());
        TextView sub = smallText("Демо-пользователь магазина DriveParts Pro");
        sub.setGravity(Gravity.CENTER);
        profile.addView(sub, matchWrap());
        profile.addView(space(14));
        LinearLayout stats = horizontal();
        stats.addView(statSmall("Заказы", orders.size() + ""), new LinearLayout.LayoutParams(0, -2, 1));
        stats.addView(spaceW(8));
        stats.addView(statSmall("Избранное", favorites.size() + ""), new LinearLayout.LayoutParams(0, -2, 1));
        stats.addView(spaceW(8));
        stats.addView(statSmall("Корзина", cartCount() + ""), new LinearLayout.LayoutParams(0, -2, 1));
        profile.addView(stats, matchWrap());
        content.addView(profile, matchWrap());
        content.addView(space(12));

        LinearLayout orderBlock = card(20, CARD);
        orderBlock.setPadding(dp(16), dp(16), dp(16), dp(16));
        orderBlock.addView(text("История заказов", 18, TEXT, true), matchWrap());
        orderBlock.addView(space(10));
        if (orders.isEmpty()) {
            orderBlock.addView(smallText("Заказов пока нет."));
        } else {
            for (Order o : orders) {
                orderBlock.addView(orderView(o));
                orderBlock.addView(space(10));
            }
        }
        content.addView(orderBlock, matchWrap());
        content.addView(space(12));

        Button logout = secondaryButton("Выйти из аккаунта");
        logout.setTextColor(RED);
        logout.setOnClickListener(v -> {
            cart.clear();
            promoPercent = 0;
            showLoginScreen();
        });
        content.addView(logout, new LinearLayout.LayoutParams(-1, dp(54)));

        root.addView(bottomNav("profile"));
        setContentView(root);
    }

    private View statSmall(String label, String value) {
        LinearLayout box = vertical();
        box.setPadding(dp(8), dp(10), dp(8), dp(10));
        box.setGravity(Gravity.CENTER);
        box.setBackground(round(BLUE_LIGHT, 16));
        TextView v = text(value, 18, PRIMARY, true);
        v.setGravity(Gravity.CENTER);
        TextView l = text(label, 11, MUTED, false);
        l.setGravity(Gravity.CENTER);
        box.addView(v, matchWrap());
        box.addView(l, matchWrap());
        return box;
    }

    private View orderView(Order o) {
        LinearLayout block = vertical();
        block.setPadding(dp(12), dp(12), dp(12), dp(12));
        block.setBackground(strokeRound(Color.WHITE, 14, BORDER));
        block.addView(text("Заказ " + o.number, 15, TEXT, true), matchWrap());
        block.addView(smallText(o.delivery));
        block.addView(space(6));
        block.addView(text(o.items, 12, MUTED, false), matchWrap());
        block.addView(space(6));
        block.addView(text("Итого: " + price(o.total) + " ₽", 15, ORANGE, true), matchWrap());
        return block;
    }

    private LinearLayout bottomNav(String selected) {
        LinearLayout nav = horizontal();
        nav.setPadding(dp(8), dp(8), dp(8), dp(8));
        nav.setGravity(Gravity.CENTER);
        nav.setBackgroundColor(Color.WHITE);
        nav.addView(navButton("Каталог", "catalog", selected, () -> showHomeScreen()), new LinearLayout.LayoutParams(0, dp(58), 1));
        nav.addView(navButton("♡ Избр.", "fav", selected, () -> showFavoritesScreen()), new LinearLayout.LayoutParams(0, dp(58), 1));
        nav.addView(navButton("🛒 " + cartCount(), "cart", selected, () -> showCartScreen()), new LinearLayout.LayoutParams(0, dp(58), 1));
        nav.addView(navButton("Профиль", "profile", selected, () -> showProfileScreen()), new LinearLayout.LayoutParams(0, dp(58), 1));
        return nav;
    }

    private Button navButton(String text, String key, String selected, Runnable action) {
        Button b = new Button(this);
        b.setText(text);
        b.setAllCaps(false);
        b.setTextSize(12);
        b.setTypeface(Typeface.DEFAULT, key.equals(selected) ? Typeface.BOLD : Typeface.NORMAL);
        b.setTextColor(key.equals(selected) ? PRIMARY : MUTED);
        b.setBackground(round(key.equals(selected) ? BLUE_LIGHT : Color.TRANSPARENT, 16));
        b.setOnClickListener(v -> action.run());
        return b;
    }

    private View topBar(String title, String subtitle, Runnable back) {
        LinearLayout bar = horizontal();
        bar.setGravity(Gravity.CENTER_VERTICAL);
        bar.setPadding(dp(10), dp(14), dp(10), dp(12));
        bar.setBackgroundColor(PRIMARY);

        if (back != null) {
            Button backBtn = new Button(this);
            backBtn.setText("←");
            backBtn.setTextSize(24);
            backBtn.setTextColor(Color.WHITE);
            backBtn.setAllCaps(false);
            backBtn.setBackgroundColor(Color.TRANSPARENT);
            backBtn.setOnClickListener(v -> back.run());
            bar.addView(backBtn, new LinearLayout.LayoutParams(dp(52), dp(52)));
        }

        LinearLayout titles = vertical();
        TextView t = text(title, 20, Color.WHITE, true);
        TextView s = text(subtitle == null ? "" : subtitle, 12, Color.rgb(221, 225, 255), false);
        titles.addView(t, matchWrap());
        titles.addView(s, matchWrap());
        bar.addView(titles, new LinearLayout.LayoutParams(0, -2, 1));

        TextView badge = text("🛒 " + cartCount(), 14, Color.WHITE, true);
        badge.setGravity(Gravity.CENTER);
        badge.setPadding(dp(10), dp(8), dp(10), dp(8));
        badge.setBackground(round(Color.argb(55, 255, 255, 255), 14));
        badge.setOnClickListener(v -> showCartScreen());
        bar.addView(badge, new LinearLayout.LayoutParams(-2, -2));
        return bar;
    }

    private List<Product> getFilteredProducts() {
        List<Product> list = new ArrayList<>();
        String q = searchQuery.toLowerCase(Locale.ROOT);
        for (Product p : products) {
            boolean categoryOk = selectedCategory.equals("Все") || p.category.equals(selectedCategory);
            boolean searchOk = q.isEmpty()
                    || p.name.toLowerCase(Locale.ROOT).contains(q)
                    || p.brand.toLowerCase(Locale.ROOT).contains(q)
                    || p.sku.toLowerCase(Locale.ROOT).contains(q)
                    || p.shortDesc.toLowerCase(Locale.ROOT).contains(q);
            if (categoryOk && searchOk) list.add(p);
        }
        if (sortMode.equals("Дешевле")) Collections.sort(list, Comparator.comparingInt(a -> a.price));
        if (sortMode.equals("Дороже")) Collections.sort(list, (a, b) -> b.price - a.price);
        if (sortMode.equals("Рейтинг")) Collections.sort(list, (a, b) -> Double.compare(b.rating, a.rating));
        return list;
    }

    private List<String> categories() {
        List<String> cats = new ArrayList<>();
        cats.add("Все");
        for (Product p : products) if (!cats.contains(p.category)) cats.add(p.category);
        return cats;
    }

    private void addToCart(Product p, int quantity) {
        for (CartItem item : cart) {
            if (item.product.id == p.id) {
                item.quantity = Math.min(99, item.quantity + quantity);
                return;
            }
        }
        cart.add(new CartItem(p, quantity));
    }

    private void changeQuantity(Product p, int delta) {
        CartItem target = null;
        for (CartItem item : cart) if (item.product.id == p.id) target = item;
        if (target != null) {
            target.quantity += delta;
            if (target.quantity <= 0) cart.remove(target);
            if (target.quantity > 99) target.quantity = 99;
        }
        showCartScreen();
    }

    private int cartCount() {
        int count = 0;
        for (CartItem item : cart) count += item.quantity;
        return count;
    }

    private int cartTotal() {
        int sum = 0;
        for (CartItem item : cart) sum += item.product.price * item.quantity;
        return sum;
    }

    private int maxDeliveryDays() {
        int max = 1;
        for (CartItem item : cart) if (item.product.deliveryDays > max) max = item.product.deliveryDays;
        return max;
    }

    private boolean isFavorite(int id) {
        return favorites.contains(id);
    }

    private void toggleFavorite(int id) {
        if (favorites.contains(id)) favorites.remove(Integer.valueOf(id));
        else favorites.add(id);
    }

    private String price(int value) {
        return NumberFormat.getIntegerInstance(new Locale("ru", "RU")).format(value);
    }

    private String shortDelivery(int days) {
        if (days == 1) return "Завтра";
        if (days == 2) return "Послезавтра";
        return "Через " + days + " дн.";
    }

    private String fullDelivery(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        String date = new SimpleDateFormat("d MMMM", new Locale("ru", "RU")).format(calendar.getTime());
        if (days == 1) return "завтра, " + date;
        if (days == 2) return "послезавтра, " + date;
        return "через " + days + " дня, " + date;
    }

    private LinearLayout vertical() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        return l;
    }

    private LinearLayout horizontal() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.HORIZONTAL);
        return l;
    }

    private TextView text(String value, int sp, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(sp);
        t.setTextColor(color);
        t.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        t.setLineSpacing(0, 1.12f);
        return t;
    }

    private TextView smallText(String value) {
        return text(value, 12, MUTED, false);
    }

    private TextView label(String value, int bg, int color) {
        TextView l = text(value, 12, color, true);
        l.setPadding(dp(10), dp(6), dp(10), dp(6));
        l.setBackground(round(bg, 14));
        return l;
    }

    private EditText input(String hint, String prefix) {
        EditText e = new EditText(this);
        e.setHint(prefix + "  " + hint);
        e.setTextSize(14);
        e.setSingleLine(false);
        e.setMinLines(1);
        e.setTextColor(TEXT);
        e.setHintTextColor(MUTED);
        e.setPadding(dp(14), dp(4), dp(14), dp(4));
        e.setBackground(strokeRound(Color.WHITE, 14, BORDER));
        return e;
    }

    private Button primaryButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setAllCaps(false);
        b.setTextSize(15);
        b.setTextColor(Color.WHITE);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setBackground(round(ORANGE, 14));
        return b;
    }

    private Button secondaryButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setAllCaps(false);
        b.setTextSize(14);
        b.setTextColor(PRIMARY);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setBackground(strokeRound(Color.WHITE, 14, BORDER));
        return b;
    }

    private Button smallButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setAllCaps(false);
        b.setTextSize(17);
        b.setTextColor(PRIMARY);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setBackground(strokeRound(Color.WHITE, 50, BORDER));
        return b;
    }

    private Button chip(String value, boolean active) {
        Button b = new Button(this);
        b.setText(value);
        b.setAllCaps(false);
        b.setTextSize(13);
        b.setTextColor(active ? Color.WHITE : TEXT);
        b.setTypeface(Typeface.DEFAULT, active ? Typeface.BOLD : Typeface.NORMAL);
        b.setBackground(active ? round(PRIMARY, 18) : strokeRound(Color.WHITE, 18, BORDER));
        b.setPadding(dp(12), 0, dp(12), 0);
        return b;
    }

    private LinearLayout card(int radius, int color) {
        LinearLayout l = vertical();
        l.setBackground(round(color, radius));
        return l;
    }

    private GradientDrawable round(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    private GradientDrawable strokeRound(int color, int radius, int stroke) {
        GradientDrawable d = round(color, radius);
        d.setStroke(dp(1), stroke);
        return d;
    }

    private GradientDrawable gradient(int c1, int c2, int radius) {
        GradientDrawable d = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{c1, c2});
        d.setCornerRadius(dp(radius));
        return d;
    }

    private Space space(int h) {
        Space s = new Space(this);
        s.setLayoutParams(new LinearLayout.LayoutParams(1, dp(h)));
        return s;
    }

    private Space spaceW(int w) {
        Space s = new Space(this);
        s.setLayoutParams(new LinearLayout.LayoutParams(dp(w), 1));
        return s;
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private View emptyCard(String title, String message) {
        LinearLayout card = card(20, CARD);
        card.setPadding(dp(24), dp(24), dp(24), dp(24));
        card.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView icon = text("🔎", 46, MUTED, false);
        icon.setGravity(Gravity.CENTER);
        card.addView(icon, matchWrap());
        TextView t = text(title, 19, TEXT, true);
        t.setGravity(Gravity.CENTER);
        card.addView(t, matchWrap());
        TextView m = text(message, 13, MUTED, false);
        m.setGravity(Gravity.CENTER);
        card.addView(m, matchWrap());
        return card;
    }

    static class Product {
        int id;
        String name, sku, shortDesc, fullDesc, icon, category, brand, compatibility, label;
        int price, stock, deliveryDays;
        double rating;

        Product(int id, String name, String sku, String shortDesc, String fullDesc, int price, String icon,
                String category, String brand, double rating, int stock, int deliveryDays, String compatibility, String label) {
            this.id = id;
            this.name = name;
            this.sku = sku;
            this.shortDesc = shortDesc;
            this.fullDesc = fullDesc;
            this.price = price;
            this.icon = icon;
            this.category = category;
            this.brand = brand;
            this.rating = rating;
            this.stock = stock;
            this.deliveryDays = deliveryDays;
            this.compatibility = compatibility;
            this.label = label;
        }
    }

    static class CartItem {
        Product product;
        int quantity;

        CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }

    static class Order {
        String number, name, phone, items, delivery;
        int total;

        Order(String number, String name, String phone, String items, int total, String delivery) {
            this.number = number;
            this.name = name;
            this.phone = phone;
            this.items = items;
            this.total = total;
            this.delivery = delivery;
        }
    }
}
