from articles import views
from django.contrib import admin
from django.urls import path

urlpatterns = [
    path(r'^article/(?P<article_id>\d+)$', views.get_article, name='get_article'),
    path('admin/', admin.site.urls),
    path('', views.archive, name='archive'),
]