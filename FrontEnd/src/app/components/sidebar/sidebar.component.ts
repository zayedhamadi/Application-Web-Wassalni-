import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  constructor() { }

  ngOnInit(): void { }
  List = [
    //1
    {
      number: '1',
      name: 'Accueil',
      icon: 'fa-solid fa-house',

      routerLink: '/Welcome',
    },
    //2

    {
      number: '2',
      name: 'Analytique',
      icon: 'fa-solid fa-chart-line',
    },
    //3
    {
      number: '3',
      name: 'Produit',
      icon: 'fa-solid fa-box',
    },
    //4
    {
      number: '4',
      name: ' Commande',
      icon: 'fa-solid fa-cart-shopping',
    },
    //5
    {
      number: '5',
      name: 'Paramètres',
      icon: 'fa-solid fa-gear',
    },
    //6
    {
      number: '6',
      name: '   À propos',
      icon: 'fa-solid fa-circle-info',
    },
    //7
    {
      number: '7',
      name: 'Contact',
      icon: 'fa-solid fa-phone',
    },

    //8
    {
      number: '8',
      name: 'Se déconnecter',
      icon: 'fas fa-power-off ',
      href: '#',
    },
  ];

  @Input() sideNavStatus: boolean = false;
  currentHoveredItem: string | null = null;

  onItemMouseEnter(item: any) {
    this.currentHoveredItem = item.name;
  }

  onItemMouseLeave() {
    this.currentHoveredItem = null;
  }
}