from .models import Article
from django.shortcuts import render
from django import template
from django.http import HttpResponse

def archive(request):
    return render(request, 'archive.html', {"posts": Article.objects.all()})