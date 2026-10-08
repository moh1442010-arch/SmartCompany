# SmartCompany — Commercial Release

الإصدار الحالي: **1.0.1**

## خط الإصدار التجاري

الملف:
`.github/workflows/build-commercial.yml`

يبني:
- APK موقّع Release للتوزيع المباشر.
- AAB للنشر على Google Play.

## متطلبات التوقيع

يجب حفظ مواد التوقيع في GitHub Actions Secrets، وليس داخل المستودع:

- `SMARTCOMPANY_KEYSTORE_B64`
- `SMARTCOMPANY_STORE_PASSWORD`
- `SMARTCOMPANY_KEY_ALIAS`
- `SMARTCOMPANY_KEY_PASSWORD`

لا تحفظ ملف keystore أو كلمات المرور في GitHub Code.

> مفتاح التوقيع التجاري أصل من أصول المنتج. فقدانه قد يمنع تحديث النسخ المثبتة لاحقًا.
