CREATE TABLE panel (
                       id BIGINT PRIMARY KEY, -- Поле наследуется от Moldings
                       panel_type VARCHAR(100) NOT NULL, -- Поле panelType, Enum хранится как строка
                       CONSTRAINT chk_panel_type CHECK (panel_type IN ('FOR_ENTRY', 'FOR_SLOPE')) -- Ограничение на значения Enum (замените TYPE1, TYPE2, TYPE3 реальными значениями)
);

CREATE TABLE pannier (
                         id BIGINT PRIMARY KEY, -- Поле наследуется от Moldings
                         molding_type VARCHAR(100) NOT NULL, -- Поле moldingType, Enum хранится как строка
                         CONSTRAINT chk_molding_type CHECK (molding_type IN ('TELESCOPIC', 'FLAT')) -- Ограничение на значения Enum (замените TYPE1, TYPE2, TYPE3 реальными значениями из MoldingType)
);

CREATE TABLE jamb (
                      id BIGINT PRIMARY KEY, -- Поле наследуется от Moldings
                      molding_type VARCHAR(100) NOT NULL, -- Поле moldingType, Enum хранится как строка
                      CONSTRAINT chk_molding_type CHECK (molding_type IN ('TELESCOPIC', 'FLAT')) -- Ограничение на значения Enum (замените TYPE1, TYPE2, TYPE3 реальными значениями из MoldingType)
);

CREATE TABLE floor_covering (
                                id BIGINT PRIMARY KEY, -- Поле наследуется от Moldings
                                water_resistance_type VARCHAR(100) NOT NULL, -- Поле waterResistanceType, Enum хранится как строка
                                CONSTRAINT chk_water_resistance_type CHECK (water_resistance_type IN ('WATER_RESISTANCE', 'PARTIAL_WATER_RESISTANCE', 'NO_WATER_RESISTANCE')) -- Ограничение на значения Enum (замените TYPE1, TYPE2, TYPE3 реальными значениями из WaterResistanceType)
);

CREATE TABLE fastening (
                           id BIGINT PRIMARY KEY -- Поле наследуется от Furniture
);

CREATE TABLE baseboard (
                           id BIGINT PRIMARY KEY, -- Поле наследуется от Moldings
                           fastening_id BIGINT, -- Связь с таблицей Fastening
                           CONSTRAINT fk_fastening FOREIGN KEY (fastening_id) REFERENCES fastening (id) -- Внешний ключ
);

CREATE TABLE additional_element (
                                    id BIGINT PRIMARY KEY, -- Поле наследуется от Moldings
                                    molding_type VARCHAR(100) NOT NULL, -- Поле moldingType, Enum хранится как строка
                                    CONSTRAINT chk_molding_type CHECK (molding_type IN ('TELESCOPIC', 'FLAT')) -- Ограничение на значения Enum (замените TYPE1, TYPE2, TYPE3 реальными значениями из MoldingType)
);

CREATE TABLE room_lock (
                           id BIGINT PRIMARY KEY, -- Поле наследуется от Lock
                           tongue_type VARCHAR(100) NOT NULL, -- Поле tongueType, Enum хранится как строка
                           lock_type VARCHAR(100) NOT NULL, -- Поле lockType, Enum хранится как строка
                           CONSTRAINT chk_tongue_type CHECK (tongue_type IN ('MECHANICAL', 'MAGNETIC')), -- Ограничение на значения Enum для tongueType
                           CONSTRAINT chk_lock_type CHECK (lock_type IN ('KEY', 'RETAINER')) -- Ограничение на значения Enum для lockType
);

CREATE TABLE room_hinge (
                            id BIGINT PRIMARY KEY -- Поле наследуется от Hinge
);

CREATE TABLE retainer (
                          id BIGINT PRIMARY KEY, -- Поле наследуется от Furniture
                          socket VARCHAR(100) NOT NULL, -- Поле socket, Enum хранится как строка
                          key_retainer VARCHAR(100) NOT NULL, -- Поле keyRetainer, Enum хранится как строка
                          CONSTRAINT chk_socket CHECK (socket IN ('SQUARE_THICK', 'SQUARE_THIN', 'ROUND_THICK', 'ROUND_THIN', 'WITHOUT_SOCKET')), -- Ограничение на значения Enum для socket
                          CONSTRAINT chk_key_retainer CHECK (key_retainer IN ('KEY_KEY', 'KEY_RETAINER', 'RETAINER')) -- Ограничение на значения Enum для keyRetainer
);

CREATE TABLE peephole (
                          id BIGINT PRIMARY KEY, -- Поле наследуется от Furniture
                          maximum_depth INT NOT NULL, -- Поле maximumDepth
                          minimum_depth INT NOT NULL, -- Поле minimumDepth
                          peephole_type VARCHAR(100) NOT NULL, -- Поле peepholeType, Enum хранится как строка
                          CONSTRAINT chk_peephole_type CHECK (peephole_type IN ('LIGHT_ENHANCED', 'BULLETPROOF', 'ELECTRIC')) -- Ограничение на значения Enum для peepholeType
);

CREATE TABLE handle (
                        id BIGINT PRIMARY KEY, -- Поле наследуется от Furniture
                        socket VARCHAR(100) NOT NULL, -- Поле socket, Enum хранится как строка
                        rod_length INT NOT NULL, -- Поле rodLength
                        CONSTRAINT chk_socket CHECK (socket IN ('SQUARE_THICK', 'SQUARE_THIN', 'ROUND_THICK', 'ROUND_THIN', 'WITHOUT_SOCKET')) -- Ограничение на значения Enum для socket
);

CREATE TABLE entry_lock (
                            id BIGINT PRIMARY KEY, -- Поле наследуется от Lock
                            defense_class VARCHAR(100) NOT NULL, -- Поле defenseClass, Enum хранится как строка
                            first_key_type VARCHAR(100) NOT NULL, -- Поле firstKeyType, Enum хранится как строка
                            second_key_type VARCHAR(100), -- Поле secondKeyType, Enum хранится как строка
                            CONSTRAINT chk_defense_class CHECK (defense_class IN ('FIRST', 'SECOND', 'THIRD', 'FOURTH')), -- Ограничение на значения Enum для defenseClass
                            CONSTRAINT chk_first_key_type CHECK (first_key_type IN ('SUVALDNY', 'CYLINDER', 'ELECTRIC', 'NONE')), -- Ограничение на значения Enum для firstKeyType
                            CONSTRAINT chk_second_key_type CHECK (second_key_type IN ('SUVALDNY', 'CYLINDER', 'ELECTRIC', 'NONE')) -- Ограничение на значения Enum для secondKeyType
);

CREATE TABLE entry_hinge (
                             id BIGINT PRIMARY KEY -- Поле наследуется от Hinge
);

CREATE TABLE room_door (
                           id BIGINT PRIMARY KEY, -- Поле наследуется от Door
                           facing VARCHAR(100) NOT NULL, -- Поле facing, Enum хранится как строка
                           handle_id BIGINT, -- Связь с таблицей handle
                           room_hinge_id BIGINT, -- Связь с таблицей room_hinge
                           retainer_id BIGINT, -- Связь с таблицей retainer
                           room_lock_id BIGINT, -- Связь с таблицей room_lock
                           jamb_id BIGINT, -- Связь с таблицей jamb
                           pannier_id BIGINT, -- Связь с таблицей pannier
                           additional_element_id BIGINT, -- Связь с таблицей additional_element
                           CONSTRAINT chk_facing CHECK (facing IN ('SPL', 'METAL', 'PLASTIC', 'LAMINATE', 'ENAMEL', 'CORTEX', 'VENEER', 'ECO_VENEER', 'MDF_PANEL')), -- Ограничение на значения Enum для facing
                           CONSTRAINT fk_handle FOREIGN KEY (handle_id) REFERENCES handle(id), -- Внешний ключ для handle
                           CONSTRAINT fk_room_hinge FOREIGN KEY (room_hinge_id) REFERENCES room_hinge(id), -- Внешний ключ для room_hinge
                           CONSTRAINT fk_retainer FOREIGN KEY (retainer_id) REFERENCES retainer(id), -- Внешний ключ для retainer
                           CONSTRAINT fk_room_lock FOREIGN KEY (room_lock_id) REFERENCES room_lock(id), -- Внешний ключ для room_lock
                           CONSTRAINT fk_jamb FOREIGN KEY (jamb_id) REFERENCES jamb(id), -- Внешний ключ для jamb
                           CONSTRAINT fk_pannier FOREIGN KEY (pannier_id) REFERENCES pannier(id), -- Внешний ключ для pannier
                           CONSTRAINT fk_additional_element FOREIGN KEY (additional_element_id) REFERENCES additional_element(id) -- Внешний ключ для additional_element
);

CREATE TABLE entry_door (
                            id BIGINT PRIMARY KEY, -- Поле наследуется от Door
                            panel_inside_id BIGINT, -- Связь с таблицей panel для panelInside
                            panel_outside_id BIGINT, -- Связь с таблицей panel для panelOutside
                            handle_id BIGINT, -- Связь с таблицей handle
                            entry_hinge_id BIGINT, -- Связь с таблицей entry_hinge
                            first_entry_lock_id BIGINT, -- Связь с таблицей entry_lock для firstEntryLock
                            second_entry_lock_id BIGINT, -- Связь с таблицей entry_lock для secondEntryLock
                            retainer_id BIGINT, -- Связь с таблицей retainer
                            color VARCHAR(100) NOT NULL, -- Поле color, Enum хранится как строка
                            metal VARCHAR(100) NOT NULL, -- Поле metal, Enum хранится как строка
                            CONSTRAINT chk_color CHECK (color IN ('BLACK', 'WHITE', 'BLUE', 'RED', 'GREEN', 'YELLOW', 'ORANGE', 'PINK', 'PURPLE', 'GRAPHITE')), -- Ограничение на значения Enum для color
                            CONSTRAINT chk_metal CHECK (metal IN ('THICKNESS_01', 'THICKNESS_02', 'THICKNESS_03', 'THICKNESS_04', 'THICKNESS_05', 'THICKNESS_06', 'THICKNESS_07', 'THICKNESS_08', 'THICKNESS_09', 'THICKNESS_10', 'THICKNESS_11', 'THICKNESS_12')), -- Ограничение на значения Enum для metal
                            CONSTRAINT fk_panel_inside FOREIGN KEY (panel_inside_id) REFERENCES panel(id), -- Внешний ключ для panelInside
                            CONSTRAINT fk_panel_outside FOREIGN KEY (panel_outside_id) REFERENCES panel(id), -- Внешний ключ для panelOutside
                            CONSTRAINT fk_handle FOREIGN KEY (handle_id) REFERENCES handle(id), -- Внешний ключ для handle
                            CONSTRAINT fk_entry_hinge FOREIGN KEY (entry_hinge_id) REFERENCES entry_hinge(id), -- Внешний ключ для entryHinge
                            CONSTRAINT fk_first_entry_lock FOREIGN KEY (first_entry_lock_id) REFERENCES entry_lock(id), -- Внешний ключ для firstEntryLock
                            CONSTRAINT fk_second_entry_lock FOREIGN KEY (second_entry_lock_id) REFERENCES entry_lock(id), -- Внешний ключ для secondEntryLock
                            CONSTRAINT fk_retainer FOREIGN KEY (retainer_id) REFERENCES retainer(id) -- Внешний ключ для retainer
);

CREATE TABLE client (
                        id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор клиента
                        register_date DATE NOT NULL, -- Дата регистрации
                        email VARCHAR(100) NOT NULL UNIQUE, -- Уникальный email клиента
                        contract_number VARCHAR(100) UNIQUE, -- Уникальный номер контракта (может быть NULL)
                        first_name VARCHAR(1000) NOT NULL, -- Имя клиента
                        second_name VARCHAR(1000), -- Отчество клиента (опционально)
                        third_name VARCHAR(1000), -- Фамилия клиента (опционально)
                        phone_number VARCHAR(12) NOT NULL UNIQUE -- Уникальный номер телефона клиента
);

CREATE TABLE employee (
                          id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор сотрудника
                          register_date DATE NOT NULL, -- Дата регистрации сотрудника
                          email VARCHAR(100) NOT NULL UNIQUE, -- Уникальный email сотрудника
                          passport VARCHAR(100) NOT NULL UNIQUE, -- Уникальный паспорт сотрудника
                          contract_number VARCHAR(100) NOT NULL UNIQUE, -- Уникальный номер контракта сотрудника
                          first_name VARCHAR(1000) NOT NULL, -- Имя сотрудника
                          second_name VARCHAR(1000) NOT NULL, -- Отчество сотрудника
                          third_name VARCHAR(1000) NOT NULL, -- Фамилия сотрудника
                          position VARCHAR(100) NOT NULL, -- Должность сотрудника (enum)
                          gender VARCHAR(25) NOT NULL, -- Пол сотрудника
                          birth_date DATE NOT NULL, -- Дата рождения сотрудника
                          phone_number VARCHAR(12) NOT NULL UNIQUE, -- Уникальный номер телефона сотрудника
                          address VARCHAR(1000) NOT NULL, -- Адрес сотрудника
                          img_path VARCHAR(300) -- Путь к изображению сотрудника
);

CREATE TABLE measurement (
                             id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор измерения
                             register_date DATE NOT NULL, -- Дата регистрации
                             address VARCHAR(1000) NOT NULL, -- Адрес измерения
                             city VARCHAR(100) NOT NULL, -- Город (enum)
                             fabric VARCHAR(100) NOT NULL, -- Материал (enum)
                             room_doors_count INT NOT NULL, -- Количество межкомнатных дверей
                             enter_doors_count INT NOT NULL, -- Количество входных дверей
                             measurement_date DATE NOT NULL, -- Дата проведения измерения
                             measurement_time TIME NOT NULL, -- Время проведения измерения
                             info VARCHAR(1000), -- Дополнительная информация
                             reminder_sent BOOLEAN, -- Флаг отправки напоминания
                             client_id BIGINT, -- Ссылка на клиента
                             employee_id BIGINT, -- Ссылка на сотрудника
                             CONSTRAINT uc_measurement UNIQUE (measurement_date, measurement_time, address), -- Уникальное ограничение
                             CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE SET NULL, -- Внешний ключ на таблицу client
                             CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE SET NULL -- Внешний ключ на таблицу employee
);

-- Создаем таблицу users
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор пользователя
                       register_date DATE NOT NULL DEFAULT CURRENT_DATE, -- Дата регистрации
                       email VARCHAR(100) NOT NULL UNIQUE, -- Уникальный email
                       password VARCHAR(100) NOT NULL, -- Пароль
                       nick_name VARCHAR(1000) NOT NULL UNIQUE, -- Уникальный никнейм
                       first_name VARCHAR(1000), -- Имя
                       second_name VARCHAR(1000), -- Фамилия
                       third_name VARCHAR(1000), -- Отчество
                       gender VARCHAR(25), -- Пол
                       birth_date DATE, -- Дата рождения
                       phone_number VARCHAR(12) UNIQUE, -- Уникальный номер телефона
                       address VARCHAR(1000), -- Адрес
                       info VARCHAR(1000), -- Дополнительная информация
                       img_path VARCHAR(300) -- Путь к изображению
);

-- Создаем таблицу cart
CREATE TABLE cart (
                      id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор корзины
                      user_id BIGINT NOT NULL -- Внешний ключ на пользователя
);

-- Добавляем внешние ключи после создания таблиц
ALTER TABLE cart
    ADD CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;
ALTER TABLE cart
    ADD CONSTRAINT unique_user_id UNIQUE (user_id);

ALTER TABLE users
    ADD CONSTRAINT fk_cart FOREIGN KEY (id) REFERENCES cart (user_id) ON DELETE CASCADE;

CREATE TABLE cart_item (
                           id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор элемента корзины
                           cart_id BIGINT NOT NULL, -- Связь с таблицей корзины
                           product_id UUID NOT NULL, -- Связь с таблицей продуктов
                           quantity INT NOT NULL CHECK (quantity > 0), -- Количество (должно быть положительным)
                           product_type VARCHAR(100) NOT NULL, -- Тип продукта
                           CONSTRAINT fk_cart_item_cart FOREIGN KEY (cart_id) REFERENCES cart (id) ON DELETE CASCADE, -- Связь с корзиной
                           CONSTRAINT chk_product_type CHECK (product_type IN (
                                                                               'ROOM_DOOR', 'ENTRY_DOOR', 'HANDLE', 'ROOM_HINGE',
                                                                               'ROOM_LOCK', 'ENTRY_LOCK', 'RETAINER', 'PEEPHOLE',
                                                                               'FASTENING', 'ADDITIONAL_ELEMENT', 'JAMB', 'PANNIER',
                                                                               'BASEBOARD', 'PANEL', 'FLOOR_COVERING'
                               )), -- Ограничение на значения enum ProductType
                           UNIQUE (cart_id, product_id, product_type) -- Уникальность продукта в корзине по типу
);
