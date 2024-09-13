<!-- Improved compatibility of back to top link: See: https://github.com/420locs/android-playground/pull/73 -->
<a id="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/420locs/android-playground">
  <!-- Todo: chèn logo đoạn này mà chưa có logo -->
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">App playground để học Android thui</h3>

  <p align="center">
  Project này sẽ là playground để apply các công nghệ mới cho một underbone Android App với mục tiêu hướng đến Kotlin Multiplatform
    <br />
    <a href="https://github.com/420locs/android-playground"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <!-- todo: Demo ném vào đây nhưng chưa có demo hehe -->
    <a href="https://github.com/420locs/android-playground">View Demo</a>
    ·
    <a href="https://github.com/420locs/android-playground/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    ·
    <a href="https://github.com/420locs/android-playground/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li>
      <a href="#architecture">Architecture</a>
      <ul>
        <li><a href="#overview-project-architecture">Overview Project Architecture</a></li>
        <li><a href="#feature-architecture">Feature Architecture</a></li>
      </ul>
    </li>
    <!-- <li><a href="#contributing">Contributing</a></li> -->
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## About The Project

<!-- todo: Chưa có ảnh screenshot luôn vì chưa ra demo mà -->
[![Product Name Screen Shot][product-screenshot]](https://example.com)

Sau này sẽ nói thêm vài thứ về cái này chứ giờ chưa nghĩ ra.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* [![Android][android-badge]][android-url]
* Jetpack Compose

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.

* Android Studio
* Azul Zulu JDK

### Installation

1. Open Project
2. Setup Gradle build using Azul Zulu JDK 17

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->

## Usage

<!-- Todo: add feature mới zô đây -->
Tạm thời chưa có feature gì nha

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->

## Roadmap

- [x] Add base code for call api: retrofit
- [x] Add base code for navigation: navigation-compose
- [x] Implement Dependency Injection: koin
- [ ] Add base consistent data:
    - [ ] data-store
    - [ ] room
- [ ] Implement savable state viewmodel
- [ ] Develop system-design
    - [ ] Toolbar
    - [ ] Button
    - [ ] Pull to refresh
    - [ ] InAppMessage
- [x] Modularize by feature:
    - [ ] Home
    - [x] Sample
    - [ ] DataStore Example
    - [ ] Firebase Example
    - [ ] InAppMessage Example
    - [ ] Notification Example
    - [ ] Pager Example
    - [ ] InnerScroll Example
- [ ] Multi-language Support
    - [ ] Vietnamese
    - [ ] other???
- [ ] Migrate to KMP(desktop, android)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Architecture

- app
- bridge
- core
    - data
        - network
        - data-store (coming soon)
    - system-design (coming soon)
- features
    - data
    - domain
    - presentation

### Overview Project Architecture

core <- features <- app
<!-- todo: should use picture to describe -->

### Feature Architecture

feature: presentation -> domain <- data

data -> bridge <- domain

presentation -> app <- bridge

<!-- CONTRIBUTING
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Top contributors:

<a href="https://github.com/420locs/android-playground/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=420locs/android-playground" alt="contrib.rocks image" />
</a>

<p align="right">(<a href="#readme-top">back to top</a>)</p> -->



<!-- LICENSE -->

## License

Nao nghĩ đến license thì ghi zô đây

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->

## Contact

Trịnh Bá Minh Ninh - [Facebook](https://fb.com/zekk01) - ninhtbm@gmail.com

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a
few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/420locs/android-playground.svg?style=for-the-badge

[contributors-url]: https://github.com/420locs/android-playground/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/420locs/android-playground.svg?style=for-the-badge

[forks-url]: https://github.com/420locs/android-playground/network/members

[stars-shield]: https://img.shields.io/github/stars/420locs/android-playground.svg?style=for-the-badge

[stars-url]: https://github.com/420locs/android-playground/stargazers

[issues-shield]: https://img.shields.io/github/issues/420locs/android-playground.svg?style=for-the-badge

[issues-url]: https://github.com/420locs/android-playground/issues

[license-shield]: https://img.shields.io/github/license/420locs/android-playground.svg?style=for-the-badge

[license-url]: https://github.com/420locs/android-playground/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/420locs

[product-screenshot]: images/screenshot.png

[android-badge]: https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white

[android-url]: https://jquery.com 