import React, { useState, useCallback, memo } from 'react';
import { ChevronDown, ChevronRight } from 'lucide-react';

// Composant pour une ligne de la structure (défini à l'extérieur)
const StructureRow = memo(({ 
  item, 
  level = 0, 
  onNameChange, 
  onSelect, 
  onToggleExpand, 
  isSelected, 
  isExpanded,
  expandedItems,
  selectedStepId 
}) => {
  const hasChildren = item.children && item.children.length > 0;

  return (
    <>
      <tr 
        className={`border-b hover:bg-gray-50 cursor-pointer ${isSelected ? 'bg-blue-50' : ''}`}
        onClick={() => onSelect(item.id)}
      >
        <td className="px-4 py-2 border-r">
          <div className="flex items-center" style={{ paddingLeft: `${level * 20}px` }}>
            {hasChildren && (
              <button
                onClick={(e) => {
                  e.stopPropagation();
                  onToggleExpand(item.id);
                }}
                className="mr-2 p-1 hover:bg-gray-200 rounded"
              >
                {isExpanded ? 
                  <ChevronDown size={16} /> : 
                  <ChevronRight size={16} />
                }
              </button>
            )}
            <span className="font-mono text-sm">{item.id}</span>
          </div>
        </td>
        <td className="px-4 py-2">
          <input
            type="text"
            value={item.name}
            onChange={(e) => onNameChange(item.id, e.target.value)}
            className="w-full p-1 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
            onClick={(e) => e.stopPropagation()}
          />
        </td>
      </tr>
      {hasChildren && isExpanded && item.children.map(child => (
        <StructureRow 
          key={child.id} 
          item={child} 
          level={level + 1}
          onNameChange={onNameChange}
          onSelect={onSelect}
          onToggleExpand={onToggleExpand}
          isSelected={selectedStepId === child.id}
          isExpanded={expandedItems.has(child.id)}
          expandedItems={expandedItems}
          selectedStepId={selectedStepId}
        />
      ))}
    </>
  );
});

// Composant JDD Table complet (défini à l'extérieur)
const JddTable = memo(({ testStepList, onJddChange }) => {
  // Fonction récursive pour collecter tous les steps
  const collectAllSteps = (data, result = []) => {
    data.forEach(item => {
      result.push(item);
      if (item.children) {
        collectAllSteps(item.children, result);
      }
    });
    return result;
  };

  const allSteps = collectAllSteps(testStepList);

  // Extraire tous les environnements uniques de tous les steps
  const environments = new Set();
  allSteps.forEach(step => {
    step.jdd.forEach(jdd => {
      Object.keys(jdd).forEach(env => environments.add(env));
    });
  });
  const envList = Array.from(environments);

  return (
    <div className="overflow-auto max-h-96">
      <table className="w-full border-collapse border border-gray-300">
        <thead className="sticky top-0 bg-gray-100">
          <tr>
            <th className="border border-gray-300 px-4 py-2 text-left bg-gray-100">ID</th>
            <th className="border border-gray-300 px-4 py-2 text-left bg-gray-100 min-w-48">Nom</th>
            {envList.map(env => (
              <th key={env} className="border border-gray-300 px-4 py-2 text-left capitalize bg-gray-100 min-w-32">
                {env}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {allSteps.map(step => {
            // Fusionner tous les JDD en un seul objet pour affichage
            const mergedJdd = {};
            step.jdd.forEach(jdd => {
              Object.keys(jdd).forEach(env => {
                mergedJdd[env] = jdd[env];
              });
            });

            return (
              <tr key={step.id} className="hover:bg-gray-50">
                <td className="border border-gray-300 px-4 py-2 font-mono text-sm">
                  {step.id}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {step.name}
                </td>
                {envList.map(env => (
                  <td key={env} className="border border-gray-300 px-2 py-1">
                    <input
                      type="text"
                      value={mergedJdd[env] || ''}
                      onChange={(e) => onJddChange(step.id, 0, env, e.target.value)}
                      className="w-full p-1 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
                  </td>
                ))}
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
});

const TestStepApp = () => {
  const [testStepList, setTestStepList] = useState([
    {
      "id": 0,
      "name": "Saisir le login",
      "jdd": [
        {"recette": "login-1"}, {"preprod": "login-2"}
      ]
    },
    {
      "id": 1,
      "name": "Saisir le password",
      "jdd": [
        {"recette": "azerty"}, {"preprod": "azerty"}
      ]
    },
    {
      "id": 2,
      "name": "Cliquer sur le bouton valider",
      "jdd": [
        {"recette": "azerty"}, {"preprod": "azerty"}
      ],
      "children": [
        {
          "id": 20,
          "name": "Saisir le login",
          "jdd": [
            {"recette": "login-1"}, {"preprod": "login-2"}
          ]
        },
        {
          "id": 21,
          "name": "Saisir le password",
          "jdd": [
            {"recette": "azerty"}, {"preprod": "azerty"}
          ]
        }
      ]
    }
  ]);

  const [selectedStepId, setSelectedStepId] = useState(0);
  const [expandedItems, setExpandedItems] = useState(new Set());

  // Fonction pour mettre à jour le nom d'un step
  const updateStepName = useCallback((id, newName, data) => {
    return data.map(item => {
      if (item.id === id) {
        return { ...item, name: newName };
      }
      if (item.children) {
        return { ...item, children: updateStepName(id, newName, item.children) };
      }
      return item;
    });
  }, []);

  // Fonction pour mettre à jour les JDD
  const updateJdd = useCallback((id, jddIndex, environment, value, data) => {
    return data.map(item => {
      if (item.id === id) {
        const newJdd = [...item.jdd];
        // Trouver l'objet qui contient cet environnement ou créer un nouveau
        let targetIndex = newJdd.findIndex(jdd => jdd.hasOwnProperty(environment));
        if (targetIndex === -1) {
          // Si l'environnement n'existe pas, l'ajouter au premier objet ou créer un nouveau
          if (newJdd.length > 0) {
            newJdd[0] = { ...newJdd[0], [environment]: value };
          } else {
            newJdd.push({ [environment]: value });
          }
        } else {
          newJdd[targetIndex] = { ...newJdd[targetIndex], [environment]: value };
        }
        return { ...item, jdd: newJdd };
      }
      if (item.children) {
        return { ...item, children: updateJdd(id, jddIndex, environment, value, item.children) };
      }
      return item;
    });
  }, []);

  // Fonction pour trouver un step par son ID
  const findStepById = useCallback((id, data) => {
    for (const item of data) {
      if (item.id === id) return item;
      if (item.children) {
        const found = findStepById(id, item.children);
        if (found) return found;
      }
    }
    return null;
  }, []);

  // Handlers stables
  const handleNameChange = useCallback((id, newName) => {
    setTestStepList(prev => updateStepName(id, newName, prev));
  }, [updateStepName]);

  const handleJddChange = useCallback((id, jddIndex, environment, value) => {
    setTestStepList(prev => updateJdd(id, jddIndex, environment, value, prev));
  }, [updateJdd]);

  const handleSelectStep = useCallback((id) => {
    setSelectedStepId(id);
  }, []);

  const handleToggleExpand = useCallback((id) => {
    setExpandedItems(prev => {
      const newSet = new Set(prev);
      if (newSet.has(id)) {
        newSet.delete(id);
      } else {
        newSet.add(id);
      }
      return newSet;
    });
  }, []);

  // Calculer le step sélectionné
  const selectedStep = findStepById(selectedStepId, testStepList);

  return (
    <div className="p-6 max-w-7xl mx-auto">
      <h1 className="text-2xl font-bold mb-6 text-gray-800">Gestion des Test Steps</h1>
      
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Premier composant : Structure hiérarchique */}
        <div className="bg-white rounded-lg shadow-md">
          <div className="bg-gray-50 px-4 py-3 border-b rounded-t-lg">
            <h2 className="text-lg font-semibold text-gray-700">Structure des Tests</h2>
          </div>
          <div className="overflow-auto max-h-96">
            <table className="w-full border-collapse">
              <thead className="sticky top-0 bg-gray-100">
                <tr>
                  <th className="border-b border-r px-4 py-2 text-left w-20 bg-gray-100">ID</th>
                  <th className="border-b px-4 py-2 text-left bg-gray-100">Nom</th>
                </tr>
              </thead>
              <tbody>
                {testStepList.map(item => (
                  <StructureRow 
                    key={item.id} 
                    item={item} 
                    level={0}
                    onNameChange={handleNameChange}
                    onSelect={handleSelectStep}
                    onToggleExpand={handleToggleExpand}
                    isSelected={selectedStepId === item.id}
                    isExpanded={expandedItems.has(item.id)}
                    expandedItems={expandedItems}
                    selectedStepId={selectedStepId}
                  />
                ))}
              </tbody>
            </table>
          </div>
        </div>

        {/* Deuxième composant : JDD */}
        <div className="bg-white rounded-lg shadow-md">
          <div className="bg-gray-50 px-4 py-3 border-b rounded-t-lg">
            <h2 className="text-lg font-semibold text-gray-700">
              Données de Test (Tous les Steps)
            </h2>
          </div>
          <div className="p-4">
            <JddTable 
              testStepList={testStepList}
              onJddChange={handleJddChange}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default TestStepApp;
