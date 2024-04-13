{
  "version": 1,
  "type": "collection",
  "title": "Course - GraphQL Core",
  "collections": [],
  "queries": [
    {
      "version": 1,
      "type": "window",
      "query": "{\n  allPlanets(first: 10, after: \"YXJyYXljb25uZWN0aW9uOjA=\") {    \n    planets {\n      name\n      climates\n      terrains\n      importantPeoples: residentConnection {\n        people: residents {\n          name\n          gender\n        }\n      }\n    }\n    pageInfo {\n      hasNextPage\n      hasPreviousPage\n      startCursor\n      endCursor\n    } \n    totalCount\n  }\n}\n",
      "apiUrl": "https://swapi-graphql.netlify.app/.netlify/functions/index",
      "variables": "{}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Star Wars - 2nd Spy Task",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "2b84efee-22b2-4739-a1b2-4f0f8aff6f01",
      "updated_at": 1666136486173,
      "postRequestScript": "",
      "postRequestScriptEnabled": false
    },
    {
      "version": 1,
      "type": "window",
      "query": "{\n  allPlanets {\n    planets {\n      name\n      climates\n      terrains\n      importantPeople: residentConnection {\n        people: residents {\n          name\n          gender\n        }\n      }\n    }\n  }\n}\n",
      "apiUrl": "https://swapi-graphql.netlify.app/.netlify/functions/index",
      "variables": "{}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Star Wars - 1st Spy Task",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "93c47306-666e-4e6e-a3a1-3f0ef280b9d3",
      "updated_at": 1666136465847,
      "postRequestScript": "",
      "postRequestScriptEnabled": false
    },
    {
      "version": 1,
      "type": "window",
      "query": "{\n  film (filmID: 1) {\n    title\n    director\n    starshipConnection {\n      starships{\n        name\n        model\n        starshipClass\n      }\n    }\n  }\n}",
      "apiUrl": "https://swapi-graphql.netlify.app/.netlify/functions/index",
      "variables": "{}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Star Wars - 3rd Spy Task",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "3aea18a0-2259-41b1-8b4b-784ef0339723",
      "updated_at": 1666136489288,
      "postRequestScript": "",
      "postRequestScriptEnabled": false
    },
    {
      "version": 1,
      "type": "window",
      "query": "fragment personData on Person {\n  name\n  eyeColor\n  gender\n  hairColor\n}\n\nquery personCompare($firstPersonID: ID, $secondPersonID: ID) {\n  firstPerson: person(personID: $firstPersonID) {\n    ...personData\n  }\n\n  secondPerson: person(personID: $secondPersonID) {\n    ...personData\n  }\n}",
      "apiUrl": "https://swapi-graphql.netlify.app/.netlify/functions/index",
      "variables": "{\n  \"firstPersonID\": 5,\n  \"secondPersonID\": 4\n}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Star Wars - Person Compare",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "cde2d3e0-c988-4708-927c-499795b228a8",
      "updated_at": 1666136492613,
      "postRequestScript": "",
      "postRequestScriptEnabled": false
    },
    {
      "version": 1,
      "type": "window",
      "query": "query {\n  oneHello {\n    ...allFields\n  }\n  \n  allHellos {\n    ...allFields\n  }\n}\n\nfragment allFields on Hello {\n  text\n  randomNumber\n}\n",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Hello GraphQL",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "1415296f-b985-4b6e-aab9-80db7c64a573",
      "created_at": 1621674792329,
      "updated_at": 1621674792329
    },
    {
      "version": 1,
      "type": "window",
      "query": "query books {\n  books(author: \"\") {\n    title\n    author {\n      name\n      originCountry\n    }\n    released {\n      year\n      printedEdition\n      releasedCountry\n    }\n  }\n}\n\n\nquery booksByReleased($releaseYear: Int = 2020, $releasePrintedEdition: Boolean) {\n  booksByReleased(\n    releasedInput: {\n      year: $releaseYear\n      printedEdition: $releasePrintedEdition\n    }\n  ) {\n    title\n    released {\n      year\n    }\n    author {\n      name\n    }\n  }\n}\n",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"releasePrintedEdition\": true,\n  \"releaseYear\": 2019\n}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Book",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "3897d80a-c616-4449-b934-8c1c2f7cb4ad",
      "updated_at": 1621922780938
    },
    {
      "version": 1,
      "type": "window",
      "query": "query mobileApps($mobileAppFilter: MobileAppFilter) {\n  mobileApps(mobileAppFilter: $mobileAppFilter) {\n    appId\n    name\n    version\n    platform\n    releaseDate\n    author {\n      name\n      originCountry\n    }\n  }\n}\n",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"mobileAppFilter\": {\n    \"releasedAfter\": \"2020-06-01\"\n  }\n}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Mobile Apps",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "3d43c441-9e85-405d-9c92-48098f8589ec",
      "updated_at": 1622026873621
    },
    {
      "version": 1,
      "type": "window",
      "query": "query pets($petFilter: PetFilter) {\n  pets(petFilter: $petFilter) {\n    __typename\n    name\n    food\n\n    ... on Dog {\n      breed\n      size\n      coatLength\n    }\n\n    ... on Cat {\n      breed\n      registry\n    }\n  }\n}\n",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Pets",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "a1c8f39f-982e-4ee9-9ece-7cee3b02ebe3",
      "updated_at": 1622095192326
    },
    {
      "version": 1,
      "type": "window",
      "query": "query ($keyword: String){\n  smartSearch(keyword: $keyword) {\n    __typename\n    \n    ...on Hello {\n      text\n    }\n    \n    ...on Book {\n      title\n    }\n  }\n}",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"keyword\": \"skull\"\n}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Union",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "924da350-da01-4e17-8485-f6a38a1f3b19",
      "updated_at": 1622165056923
    },
    {
      "version": 1,
      "type": "window",
      "query": "mutation addHelloMutation($newHello: HelloInput!) {\n  addHello(helloInput: $newHello)\n}\n\nmutation replaceHelloTextMutation($helloReplacement: HelloInput!) {\n  replaceHelloText(helloInput: $helloReplacement) {\n    text\n    randomNumber\n  }\n}\n\nmutation deleteHelloMutation($number: Int!) {\n  deleteHello(number: $number)\n}\n\nquery allHellos {\n  allHellos {\n    text\n    randomNumber\n  }\n}\n",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Hello Mutation",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "07c4daf9-2407-4fb5-9af6-cd4a0e59ac2c",
      "created_at": 1621674792329,
      "updated_at": 1621674792329
    },
    {
      "version": 1,
      "type": "window",
      "query": "query {\n  additionalOnRequest\n}",
      "apiUrl": "http://localhost:8080/graphql?optionalParam=xxx&mandatoryParam=yyy",
      "variables": "{}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "mandatoryHeader",
          "value": "Mandatory header value",
          "enabled": true
        }
      ],
      "windowName": "Course - Request Header / Param",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "ebfb5b76-76df-430c-b867-bc9b8f41cfed",
      "updated_at": 1621674792329,
      "created_at": 1621674792329
    },
    {
      "version": 1,
      "type": "window",
      "query": "subscription getStockEveryInterval {\n  randomStock {\n    symbol\n    price\n  }\n}\n",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{}",
      "subscriptionUrl": "ws://localhost:8080/subscriptions",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Stock Subscription",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "f1075576-35b2-4fd7-bd0f-e6133da8969b",
      "updated_at": 1621674792329,
      "created_at": 1621674792329
    },
    {
      "version": 1,
      "type": "window",
      "query": "query latestProblems {\n  problemLatestList {\n    id\n    prettyCreateDateTime\n    title\n    content\n    tags\n    solutionCount\n    createDateTime\n    solutions {\n      id\n      content\n      prettyCreateDateTime\n      createDateTime\n    }\n  }\n}\n\nquery problemDetail($problemId: ID!) {\n  problemDetail(id: $problemId) {\n    id\n    prettyCreateDateTime\n    title\n    content\n    solutionCount\n    solutions {\n      content\n      prettyCreateDateTime\n      voteAsBadCount\n      voteAsGoodCount\n    }\n  } \n}\n\nmutation createProblem($newProblem: ProblemCreateInput!) {\n  problemCreate(problem: $newProblem) {\n    problem {\n      id\n      prettyCreateDateTime\n    }\n  }\n}",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"newProblem\": {\n    \"title\": \"New problem 1\",\n    \"content\": \"Test\",\n    \"tags\": [\"lung\", \"skin\"]\n  }\n}",
      "subscriptionUrl": "ws://localhost",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "authToken",
          "value": "NaFdGkI1G1sol5LigjmD1xEumx2CE3C57wHpFEvi",
          "enabled": true
        }
      ],
      "windowName": "Course - Problemz",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "f73547db-954d-4731-a4c2-e6708f2b7e53",
      "updated_at": 1621983714484
    },
    {
      "version": 1,
      "type": "window",
      "query": "query itemSearch($filter: SearchItemFilter) {\n  itemSearch(filter: $filter) {\n  \ttype: __typename\n    content\n    prettyCreateDateTime\n    createDateTime\n    author {\n      username\n    }\n    ...on Problem {\n      title\n      tags\n      solutionCount\n      solutions {\n        id\n        content\n      }\n    }\n  }\n}",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"filter\": {\n    \"keyword\": \"heart\"\n  }\n}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Item Search",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "269ae34c-2daf-4024-b8f2-41ef53817d89",
      "updated_at": 1621674792329,
      "created_at": 1621674792329
    },
    {
      "version": 1,
      "type": "window",
      "query": "mutation login($credential: UserLoginInput!) {\n  userLogin(user: $credential) {\n    user {\n      username\n    }\n    authToken {\n      authToken\n    }\n  }\n}\n\nquery accountInfo {\n  me {\n    username\n    email\n    displayName\n    avatar\n  }\n}",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"credential\": {\n    \"username\": \"grosin\",\n    \"password\": \"password\"\n  }\n}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Userz",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "673476c2-fd42-4874-a014-aead708e722d",
      "updated_at": 1622349977819
    },
    {
      "version": 1,
      "type": "window",
      "query": "mutation createSolution($newSolution: SolutionCreateInput!) {\n  solutionCreate(solution: $newSolution) {\n    solution {\n      id\n      prettyCreateDateTime\n    }\n  }\n}\n\nmutation voteSolution($newVote: SolutionVoteInput!) {\n  solutionVote(vote: $newVote) {\n    solution {\n      id\n      voteAsBadCount\n      voteAsGoodCount\n    }\n  }\n}",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"newVote\": {\n    \"solutionId\": \"3ad12ad2-53f3-459f-9409-597e41c6f5c4\",\n    \"voteAsGood\": true\n  }\n}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "authToken",
          "value": "xxMlCNY5NCPxrJBkCoOreTLybWhMUeKWioKq4Cbv",
          "enabled": true
        }
      ],
      "windowName": "Course - Solutionz",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "e8a055a3-a582-4aa1-96fe-22549f48e3ae",
      "updated_at": 1621983798533
    },
    {
      "version": 1,
      "type": "window",
      "query": "subscription problemAdded {\n  problemAdded {\n    prettyCreateDateTime\n    title\n    content\n  }\n}",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"solutionId\": \"3ad12ad2-53f3-459f-9409-597e41c6f5c4\"\n}",
      "subscriptionUrl": "ws://localhost:8080/subscriptions",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Problemz Subscription",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "97e49e21-5a4f-4c3d-9ae6-795e69ea5b28",
      "updated_at": 1621983956432
    },
    {
      "version": 1,
      "type": "window",
      "query": "subscription solutionVoted($solutionId: ID!) {\n  solutionVoteChanged(solutionId: $solutionId) {\n    id\n    prettyCreateDateTime\n    voteAsBadCount\n    voteAsGoodCount\n  }\n}",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"solutionId\": \"280ba762-60a6-4bb1-b1b7-b1153f4c94c9\"\n}",
      "subscriptionUrl": "ws://localhost:8080/subscriptions",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Solutionz Vote Subscription",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "54914f32-89ca-4804-b4ab-95121a4ea719",
      "updated_at": 1652605427493
    },
    {
      "version": 1,
      "type": "window",
      "query": "query allPlanets {\n  allPlanets {\n    planets {\n      name\n      climates\n      terrains\n    }\n  }\n}\nquery oneStarshipFixed {\n  starship(id: \"c3RhcnNoaXBzOjU=\") {\n    model\n    name\n    manufacturers\n  }\n}\nquery oneFilm($filmId: ID!) {\n  film(filmID: $filmId) {\n    title\n    director\n    releaseDate\n  }\n}\n",
      "apiUrl": "https://swapi-graphql.netlify.app/.netlify/functions/index",
      "variables": "{}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "",
          "value": "",
          "enabled": true
        }
      ],
      "windowName": "Course - Starwars Client",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "eb836e25-ea2f-48fd-8423-a44f2273c0e1",
      "updated_at": 1622531021380
    },
    {
      "version": 1,
      "type": "window",
      "query": "mutation createUser($newUser: UserCreateInput!) {\n  userCreate(user: $newUser){\n    user {\n      id\n      username\n    }\n  }\n}\n\nmutation userActivation($userActivation: UserActivationInput!) {\n  userActivation(user: $userActivation) {\n    isActive\n  }\n}",
      "apiUrl": "http://localhost:8080/graphql",
      "variables": "{\n  \"userActivation\": {\n    \"username\": \"test99\",\n    \"active\": false\n  }\n}",
      "subscriptionUrl": "",
      "subscriptionConnectionParams": "{}",
      "headers": [
        {
          "key": "authToken",
          "value": "QOyUgDWEVO9IL2hdrmA9FERGrJdKmQmQ9Nqol8eM",
          "enabled": true
        }
      ],
      "windowName": "Course - Userz Administration",
      "preRequestScript": "",
      "preRequestScriptEnabled": false,
      "id": "6354895c-d0dc-4175-96f4-89d3ac074fdd",
      "updated_at": 1622762821134
    }
  ],
  "created_at": 1621674792329,
  "updated_at": 1622762821134,
  "id": "12"
}